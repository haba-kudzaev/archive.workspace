/*!
 * @author      codev01 <codev01.private@gmail.com>
 * @copyright   Copyright (c) Haba Kudzaev, 2019
 * @license     MIT License
 * @created     2019-05-03
 */

/*!
 * @dependency  jquery.js:3.4.0+
 * @dependency  microui.css:16+
 */
 
// MicroUI => ToolbarColor
$(function() {
	
	var colorPrimary = '202125';
	var meta4Chrome = '<meta name="theme-color" content="#' + colorPrimary + '">',
		meta4Safari = '<meta name="apple-mobile-web-app-status-bar-style" content="#' + colorPrimary + '">',
		meta4WinPhone = '<meta name="msapplication-navbutton-color" content="#' + colorPrimary + '">';
		
	$('head').append(meta4Chrome + meta4Safari + meta4WinPhone);
	
});

// MicroUI => Ripple effect
$(function($) {

	$(document).on("mousedown", "[data-ripple]", function(e) {
		
		var a = $(this),
			p = a.css("position"),
			o = a.offset(),
			x = e.pageX - o.left,
			y = e.pageY - o.top,
			d = Math.min(this.offsetHeight, this.offsetWidth, 100),
			r = $('<div/>', { 
				class: "ripple", 
				appendTo: a 
			});
			
		if (a.is("[disabled]")) {
			return;
		} else if (a.is("[data-ripple='static']")) {
			return;
		} 
		
		if (a.closest("[data-ripple]")) {
			e.stopPropagation();
		}
		
		if (!p || p === "static") {
			a.css({
				position: "relative"
			});
		}
		
		$("<div/>", {
			class: "ripple-wave",
			css: {
				background: a.data("ripple"),
				width: d,
				height: d,
				left: x - (d/2),
				top: y - (d/2),
			},
			appendTo : r,
			one : {
				animationend : function(){
					r.remove();
				}
			}
		});
		
	});
	
});
