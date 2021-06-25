//加载json文件，内部包含图标样式列表
function getIconfontsClassList (css_prefix_text, glyphsList) {
  let classList = []
  if (glyphsList !== undefined && glyphsList !== null) {
    for (let item of glyphsList) {
      item.font_class = css_prefix_text + item.font_class
      classList.push(item)
    }
  }

  return classList
}
//导入所有css文件，导入后在页面中可以直接引用css中的class
const req = require.context('@/assets/externalIcons/iconfonts', true, /\.css$/)
const requireAll = requireContext => requireContext.keys().map(requireContext)
requireAll(req)
// const externalIconProjectMap={}//所有图片项目的名称列表
const map = {}
function getIconProjectList () {
  const ctx = require.context('@/assets/externalIcons/iconfonts', true, /\.json$/)
  let list = []
  for (const key of ctx.keys()) {
    let fileKey = key.replace(/\.\/|\.json/g, '');
    let projectNames = fileKey.split('/')
    if (projectNames !== undefined && projectNames !== null && projectNames.length > 0
      && ctx(key).font_family !== undefined && ctx(key).font_family !== null && ctx(key).font_family === 'iconfont') {
      list.push({
        pathName: projectNames[0],
        value: list.length,
        id: ctx(key).id,
        name: ctx(key).name,
        font_family: ctx(key).font_family,
        css_prefix_text: ctx(key).css_prefix_text,
        description: ctx(key).description,
        classNameList: getIconfontsClassList(ctx(key).css_prefix_text, ctx(key).glyphs)
      })
    }
  }
  return list
}
//获取所有的class
// export default externalIconProjectMap
const iconProjectList = getIconProjectList()
export default iconProjectList