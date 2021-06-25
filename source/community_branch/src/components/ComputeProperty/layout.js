import Vue from 'vue'
import layoutProperty from '@/components/ComputeProperty/layoutProp'
const setDataContentLayout = (dataTopHeight, dataLeftWidth, dataRightWidth) => {
  layoutProperty.data_top_height = dataTopHeight
  layoutProperty.data_left_width = dataLeftWidth
  layoutProperty.data_right_width = dataRightWidth
  //
  if (layoutProperty.ele !== undefined && layoutProperty.ele !== null && layoutProperty.ele.offsetWidth === 0) layoutProperty.ele = null
  if (layoutProperty.ele === null) {
    layoutProperty.ele = document.getElementById('dataContainer')
  }
  let eleWidth = layoutProperty.ele.offsetWidth
  let eleHeight = layoutProperty.ele.offsetHeight
  layoutProperty.data_ContentPanel_height = eleHeight - dataTopHeight
  layoutProperty.data_middle_width = eleWidth - dataLeftWidth - dataRightWidth - 2
}
const refreshDataContentLayout = () => {
  console.log('layoutProperty.data_ContentPanel_height' + layoutProperty.data_ContentPanel_height)
  setDataContentLayout(layoutProperty.data_top_height, layoutProperty.data_left_width, layoutProperty.data_right_width)
}
export default { setDataContentLayout, refreshDataContentLayout }
