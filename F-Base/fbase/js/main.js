var PROJECT = {
	PROJECT_NAME: "fbase",
	PROJECT_TITLE: "F-Base"
};

var MANIFEST = {
	VERSION: 2,
	VERSION_ASSETS: 5,
	BRANCH: "test", /* beta / release / test */
	CODENAME: "madina",
	BUILD_DATE: "2018" + "10" + "21",
	BUILD: function() {
		return "FBASE-" + this.VERSION + "." + this.VERSION_ASSETS + "-" + this.BUILD_DATE + "-" + this.CODENAME + "-" + this.BRANCH;
	}
};

var TEST = true;

function footerInfo() {
	document.getElementById("isVersion").innerHTML = "<b>" + MANIFEST.VERSION + "</b>";
	document.getElementById("isAssetsVersion").innerHTML = "<b>" + MANIFEST.VERSION_ASSETS + "</b>";
	document.getElementById("isBuildInfo").innerHTML = "<b>" + MANIFEST.BUILD() + "</b>";
	
	if (MANIFEST.BRANCH == "beta") {
		document.getElementById("isBranch").innerHTML = "<b>" + "В разработке (" + MANIFEST.BRANCH + ")</b>";
	} else if (MANIFEST.BRANCH == "release") {
		document.getElementById("isBranch").innerHTML = "<b>" + "Финальная версия (" + MANIFEST.BRANCH + ")</b>";
	} else if (MANIFEST.BRANCH == "test") {
		document.getElementById("isBranch").innerHTML = "<b>" + "Тестирование (" + MANIFEST.BRANCH + ")</b>";
	}
	
} footerInfo();

function frameworkStatus() {
	var STATUS_BETA = PROJECT.PROJECT_TITLE + " " + MANIFEST.VERSION + "." + MANIFEST.VERSION_ASSETS + " находится в разработке...",
		STATUS_RELEASE = PROJECT.PROJECT_TITLE + " " + MANIFEST.VERSION + "." + MANIFEST.VERSION_ASSETS + " доступен для загрузки!<br>" + PROJECT.PROJECT_TITLE + " " + MANIFEST.VERSION + "." + (MANIFEST.VERSION_ASSETS + 1) + " находится в разработке...",
		STATUS_TEST = PROJECT.PROJECT_TITLE + " " + MANIFEST.VERSION + "." + MANIFEST.VERSION_ASSETS + " тестируется...";
		
	if (MANIFEST.BRANCH == "beta") {
		document.getElementById("fbaseStatus").innerHTML = STATUS_BETA;
	} else if (MANIFEST.BRANCH == "release") {
		document.getElementById("fbaseStatus").innerHTML = STATUS_RELEASE;
	} else if (MANIFEST.BRANCH == "test") {
		document.getElementById("fbaseStatus").innerHTML = STATUS_TEST;
	}
	
	var d = new Date();
	document.getElementById("copyrightYear").innerHTML = d.getFullYear();
	
	if (TEST == true) {
		document.getElementById("alertProjectTest").style.display = "block";
	} else {
		document.getElementById("alertProjectTest").style.display = "none";
	}
} frameworkStatus();

function secretPage() {
	// конечно, это не для безопасности.
	// но пока не хочу, чтобы документацию кто-то видел))
	var mAlert = prompt("Введите пароль", "");
	
	var PASSWORDS = "f1qbk";
	
	if (mAlert == "") {
		alert("Не верно!");
	} else if (mAlert == PASSWORDS) {
		open("fbase/html/page_documentation.html");
	} else if (mAlert != PASSWORDS) {
		alert("Не верно!");
	}
}
