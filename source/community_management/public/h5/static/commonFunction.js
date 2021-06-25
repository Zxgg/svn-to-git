export default {
	checkIsNotNull: function (checkValue) {
	    if (checkValue !== undefined && checkValue !== null) return true
	    return false
	  },
	getStringFromList (list, separChar = ',') {
	    if (this.checkIsNotNull(list) && list.length > 0) {
	      return list.join(separChar)
	    } else {
	      return ''
	    }
	  },
}