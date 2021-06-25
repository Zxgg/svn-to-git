#!/bin/sh
SYSTEMDDIR=/usr/lib/systemd/system
SERVICEFILE=docker.service
DOCKERDIR=/usr/bin
DOCKERBIN=docker
SERVICENAME=docker
FILETARGZ=docker-17.03.2-ce.tgz
mkdir -p /usr/lib/systemd/system
mkdir -p /etc/sysconfig
if [ ! -f ${FILETARGZ} ]; then
  echo "Docker binary tgz files does not exist, please check it"
  exit 1
fi

echo "##unzip : tar xvpf ${FILETARGZ}"
tar xvpf ${FILETARGZ}
echo

echo "##binary : ${DOCKERBIN} copy to ${DOCKERDIR}"
cp -p ${DOCKERBIN}/* ${DOCKERDIR} >/dev/null 2>&1
which ${DOCKERBIN}

echo "##systemd service: ${SERVICEFILE}"
echo "##docker.service: create docker systemd file"
cat >${SYSTEMDDIR}/${SERVICEFILE} <<"EOF"
[Unit]
Description=Docker Application Container Engine
Documentation=http://docs.docker.com
After=network.target docker.socket
[Service]
Type=notify
EnvironmentFile=/etc/sysconfig/docker
ExecStart=/usr/bin/dockerd $other_args
LimitNOFILE=1048576
LimitNPROC=1048576
LimitCORE=infinity
TimeoutStartSec=0
Restart=on-abnormal
MountFlags=slave
[Install]
WantedBy=multi-user.target
EOF

echo "##/etc/sysconfig/docker"
cat >/etc/sysconfig/docker <<EOF
# /etc/sysconfig/docker
other_args='-H tcp://0.0.0.0:4243 -H unix:///var/run/docker.sock'
EOF

echo ""

systemctl daemon-reload
systemctl enable docker
systemctl restart docker
echo "success"
echo ""
