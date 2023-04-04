var validate = {
	check : function (p, t) {
		for (let a in t) {
			if (p[a].trim() === '') {
				alert(t[a]);
				return true;
			}
		}
	}
};