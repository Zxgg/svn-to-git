import Vue from 'vue'
import App from './App'
import wangdingPickerAddress from '@/components/wangding-pickerAddress/wangding-pickerAddress.vue'
		Vue.component('wangdingPickerAddress',wangdingPickerAddress);
import xflSelect from './components/xfl-select/xfl-select.vue';
		Vue.component('xfl-select', xflSelect);
import bjxForm from '@/components/bjx-form/bjx-form-item.vue'
		Vue.component('bjxForm', bjxForm);
import bjxFormItem from '@/components/bjx-form/bjx-form-item.vue'
		Vue.component('bjxFormItem', bjxFormItem);
import robbyImageUpload from '@/components/robby-image-upload/robby-image-upload.vue'
		Vue.component('robbyImageUpload', robbyImageUpload);
import xpPicker from '@/components/xp-picker/xp-picker.vue'
		Vue.component('xpPicker', xpPicker);
import imgUpload from '@/components/ay-img-upload/ay-img-upload.vue';
		Vue.component('imgUpload', imgUpload);
import webconfig from '@/config/webconfig.js'
import marked from 'marked'
import uParse from '@/components/gaoyia-parse/parse.vue'
		Vue.component('uParse', uParse);

Vue.config.productionTip = false

App.mpType = 'app'

webconfig().then(() =>{
const app = new Vue({
    ...App
}) 
app.$mount()
})
