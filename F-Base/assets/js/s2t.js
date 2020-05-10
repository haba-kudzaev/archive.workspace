/*!
 * @author      codev01 <codev01.private@gmail.com>
 * @copyright   Copyright (c) S-Devs, 2018
 */

var s2t = function() {
	if (document.body.scrollTop > 0 || document.documentElement.scrollTop > 0) {
		window.scrollBy(0, -50);
		setTimeout(s2t, 10);
	}
};

window.onscroll = function() {
	var scrolled = window.pageYOffset || document.documentElement.scrollTop;
	if (scrolled > 100) {
		document.getElementById("s2t").style.display = "block";
	} else {
		document.getElementById("s2t").style.display = "none";
	}
};
