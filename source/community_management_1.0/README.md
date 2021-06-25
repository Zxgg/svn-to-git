## 开发

```bash
# 克隆项目
git clone https://gitee.com/y_project/RuoYi-Vue

# 进入项目目录
cd ruoyi-ui

# 安装依赖
npm install

# 建议不要直接使用 cnpm 安装依赖，会有各种诡异的 bug。可以通过如下操作解决 npm 下载速度慢的问题
npm install --registry=https://registry.npm.taobao.org

# 启动服务
npm run dev
```

浏览器访问 http://localhost:80

## 发布

```bash
# 构建测试环境
npm run build:stage

# 构建生产环境
npm run build:prod
```


## 注意点
```bash   
# 1 . sass 加载失败的话
 npm config set sass_binary_site=https://npm.taobao.org/mirrors/node-sass
 npm install --registry=https://registry.npm.taobao.org

# 2 . npm i 编译出错后，执行  npm rebuild node-sass --force 重新加载 node-sass
 npm rebuild node-sass --force