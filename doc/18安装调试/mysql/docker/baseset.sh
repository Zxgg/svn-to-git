#!/bin/sh
swapoff -a
# CentOS关闭selinux
sed -i 's/SELINUX=enforcing/SELINUX=disabled/g' /etc/selinux/config
# 关闭防火墙
systemctl stop firewalld.service && systemctl disable firewalld.service
# Kernel性能调优
cat >> /etc/sysctl.conf<<EOF
net.ipv4.ip_forward=1
net.ipv4.conf.all.rp_filter = 0
net.ipv4.conf.default.rp_filter = 0
net.ipv4.conf.docker0.rp_filter = 2
net.ipv4.conf.ens33.rp_filter = 2
net.ipv4.conf.flannel/1.rp_filter = 0
net.ipv4.conf.lo.rp_filter = 0
net.ipv4.conf.virbr0.rp_filter = 0
net.ipv4.conf.virbr0-nic.rp_filter = 0
net.bridge.bridge-nf-call-iptables=1
net.ipv4.ip_nonlocal_bind=1
net.ipv4.neigh.default.gc_thresh1=4096
net.ipv4.neigh.default.gc_thresh2=6144
net.ipv4.neigh.default.gc_thresh3=8192
EOF
sysctl -p
sysctl -p
sysctl -p
echo "the base set end"
