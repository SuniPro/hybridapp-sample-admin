var excel_math = {
	roundup : function (v, p) {
		let digits = Math.pow(10, p);
		let num = Math.ceil(v * digits) / digits;
		return num.toFixed(p);
	}
};