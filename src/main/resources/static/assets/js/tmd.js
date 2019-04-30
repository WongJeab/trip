(function($){

	//select双向选择
	var TMDdualListbox = function(json){
		return new TMDdualListbox.init(this,json)
	}

	TMDdualListbox.init = function(object,json){
		this.option = {
			title:true,
			titleText : ['标题一','标题二'],
			selected : [],
			height:200,
			width:'100%',
		}
		if(json){
			for(var item in json){
				this.option[item] = json[item]
			}

		}
		this.object = $(object);
		this.Dom = $(object).parent();
		this.childrens = $(object).children().clone();
		this.values = this.objectValues(this.childrens);
		this.object.hide();

		this.dualListboxHTML =  "<div class='row select-dualListbox' style='height:"+this.option.height+"px;width:"+this.option.width+"' >"+
										"<div class='col-sm-5 select-dualListbox-row'>"+
											(this.option.title?"<div class='select-dualListbox-title'>"+this.option.titleText[0]+"</div>":'')+
											"<select multiple='multiple' alias='oRiginal' style='height:"+(this.option.title?this.option.height-40:'100%')+"px' >"+
											"</select>"+
										"</div>"+
								"<div class='col-sm-2 jui-layout-table'>"+
								"<div class='jui-layout-table-cell'>"+
									"<button class='btn dualList-right'><i class='glyphicon glyphicon-arrow-right'></i></button>"+
									"<button class='btn dualList-left'><i class='glyphicon glyphicon-arrow-left'></i></button>"+
								"</div>"+
								"</div>"+
										"<div class='col-sm-5 select-dualListbox-row'>"+
										(this.option.title?"<div class='select-dualListbox-title'>"+this.option.titleText[1]+"</div>":'')+
										"<select multiple='multiple' alias='oSelected' style='height:"+(this.option.title?this.option.height-40:'100%')+"px' >"+
										"</select>"+
										"</div>"+
								"</div>";
		this.Dom.append(this.dualListboxHTML)	
		this.RestHTML();
		var that = this;

		this.Dom.on("click","select",function(e){
			if(!$(this).val()){
				return false;
			}
			var val = $(this).val()[0];
			var i = $.inArray(val,that.option.selected);
			i<0?that.option.selected.push(val):that.option.selected.splice(i,1);
			that.RestHTML();
			return false;
		})
		this.Dom.on("click",".dualList-right",function(e){
			that.option.selected = that.values;
			that.RestHTML();
			return false;
		})
		this.Dom.on("click",".dualList-left",function(e){
			that.option.selected = [];
			that.RestHTML();
			return false;
		})
	}

	TMDdualListbox.init.prototype.objectValues = function(objects){
		var arr = [];
		for(var i =0 ;i<objects.length;i++){
			arr.push(objects.eq(i).val())
		}
		return arr;
	}
	TMDdualListbox.init.prototype.selectedArray = function(array){
		this.childrens = this.object.children().clone();
		var oSelected = [];
		var oRiginal = []; 
		var aaa = []
		
		for(var i = 0;i < this.childrens.length; i++){
			for(var l = 0;l < array.length; l++){
				if(this.childrens.eq(i).val() === array[l]){
					oSelected.push(this.childrens.eq(i));
				}else{
					oRiginal.push(this.childrens.eq(i));
				}
			}
		}
		if(oRiginal.length == 0){
			return [this.childrens,oSelected]
		}else{
			return [oRiginal,oSelected]
		}	
	}

	TMDdualListbox.init.prototype.RestHTML = function(array){

		var options = this.selectedArray(this.option.selected);
		 this.Dom.find(".select-dualListbox-row select").eq(0).html(options[0]);
		 this.Dom.find(".select-dualListbox-row select").eq(1).html(options[1]);
	}

	TMDdualListbox.init.prototype.getValues = function(){
		return this.option.selected;
	}

	//file图片预览
	var TMDfileImg = function(){
		$(this).on("change",function(){
			 var prevDiv = $(this).siblings('[filepreview]'); 
			 var file =  $(this).get(0);
			 var prevImg = '';
			 if (file.files && file.files[0]){  
				 var reader = new FileReader();  
				 reader.onload = function(evt){  
				 prevImg = '<img src="' + evt.target.result + '" />';
				 prevDiv.html(prevImg);
				}    
				 reader.readAsDataURL(file.files[0]);  
			}else{  
				file.select();
				file.blur();
				var imgSrc = 'file:///'+document.selection.createRange().text;
				prevDiv.css("filter","progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod='scale',src=\"" + imgSrc + "\")")
				//prevImg = '<div class="img" style="filter:progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=scale,src=\'' + imgSrc + '\'"></div>';
				prevDiv.html(prevImg);   
			 } 
		})
	}

	//局部模块添加删除
	var TMDtabbable = function(options){
		var tab,
			tabCon,
			$i = 0,
			that = this;
		if(!options || !options.tab || !options.tabCon){
			console.log("参数错误")
			return false;
		}
		$(this).find(options.plus).click(function(){
				tab = options.tab.replace('{i}',$i);
				tabCon = options.tabCon.replace('{i}',$i);
				$(tab).insertBefore(this);
				$(that).find(options.tabConAppend).append($(tabCon))
				if(options.createFun){
					options.createFun($i)
				}
				$i++;
		})
	}

	//多重选择插件比如城市选择
	var TMDchoose = function(options){
		return new TMDchoose.init(this,options);
	}

	TMDchoose.init = function(object,options){
		var _self = $(object);
		var that = this;
		var discern = new Date().getTime();
		var stairHTML = '';
		this.option = {
			stairData:[],
			secondaryData:{},
		};
		this.values = {};
		if(options){
			for(var item in options){
				this.option[item] = options[item]
			}

		}
		for(var i = 0; i<this.option.stairData.length; i++){
			stairHTML+='<label class="TMDchoose-checkbox">'+
							'<input name="'+discern+'" type="checkbox" class="ace TMDchoose-stair" value="'+this.option.stairData[i]+'" />'+
								'<span class="lbl"> '+this.option.stairData[i]+' </span>'+
							'</label>'

		}
		stairHTML += '<a class="btn jui-theme-btn TMDchoose-shrink">展开选项</a>'
		_self.html(stairHTML)
		_self.on("click",".TMDchoose-shrink",function(){
			if(_self.hasClass("show")){
				_self.removeClass("show");
				$(this).html('展开选项');
			}else{
				_self.addClass("show");
				$(this).html('隐藏部分')
			}
		})
		_self.on("change",".TMDchoose-checkbox .TMDchoose-stair",function(){
				var checked =$(this).is(':checked');
				var val = $(this).val();

				if(!(val in that.option.secondaryData)){
					checked?that.values[val] = '':delete that.values[val];
					return false;
				}
				if(checked){
					if(_self.find(".TMDchoose-secondary").length>0){
						_self.find(".TMDchoose-secondary").siblings(".TMDchoose-stair").attr('checked',false);
						_self.find(".TMDchoose-secondary").remove();
					}
					$(this).parent().append(that.secondaryHTML(val));
					setTimeout(function(){
						$(".TMDchoose-secondary").addClass("show")	
					},30)
				}else{
					//_self.find(".TMDchoose-secondary").remove();
					if(val in that.values){
						delete that.values[val]
					}
				}
		})
		_self.on("click",".TMDchoose-checkbox .TMDchoose-close",function(e){
				$(this).parents(".TMDchoose-secondary").siblings(".TMDchoose-stair").attr('checked',false);
				$(this).parents(".TMDchoose-secondary").remove();
				return false;
		});
		_self.on("click",".TMDchoose-checkbox .TMDchoose-true",function(e){
				e.stopPropagation();
				var res = [];
				var dom = $(this).parents(".TMDchoose-secondary").find(".TMDchoose-secondary-input");
				var jsonName =$(this).parents(".TMDchoose-secondary").siblings(".TMDchoose-stair").val();
				for(var i=0;i<dom.length;i++){
					dom.eq(i).is(':checked')?res.push(dom.eq(i).val()):null;
				}
				that.values[jsonName] = res;
				$(this).parents(".TMDchoose-secondary").remove();
				return false;
		});
		_self.on("click",".TMDchoose-checkbox .TMDchoose-against",function(e){
				var dom = $(this).parents(".TMDchoose-secondary").find(".TMDchoose-secondary-input");
				for(var i=0;i<dom.length;i++){
					dom.eq(i).is(':checked')?dom.eq(i).attr('checked',false):dom.eq(i).attr('checked',true);
				}
				return false;
		});
	}

	TMDchoose.init.prototype.secondaryHTML = function(index){
		var discern = new Date().getTime();
		var secondaryHTMLchoose ='',
			secondaryHTML = '';
		if(this.option.secondaryData[index]){
			for(var i = 0; i<this.option.secondaryData[index].length; i++){
				secondaryHTMLchoose += '<label class="TMDchoose-checkbox">'+
							'<input name="'+discern+'" type="checkbox" class="ace TMDchoose-secondary-input" value="'+this.option.secondaryData[index][i]+'" />'+
								'<span class="lbl"> '+this.option.secondaryData[index][i]+' </span>'+
							'</label>'
			}
		}else{
			return false;
		}
		
		 
		 secondaryHTML = '<div class="TMDchoose-secondary">'+
								'<div class="TMDchoose-secondary-title">选择详情</div>'+
								'<div class="TMDchoose-secondary-content jui-justify jui-justify-g">'+secondaryHTMLchoose+'</div>'+
								'<div class="TMDchoose-secondary-footer align-center">'+
								'<a class="TMDchoose-btn btn-lightgreen TMDchoose-true"><i class="ace-icon fa fa-check"></i></a>'+
								'<a class="TMDchoose-btn btn-violet TMDchoose-against "><i class="ace-icon fa fa-exchange"></i></a>'+
								'<a class="TMDchoose-btn btn-gray TMDchoose-close"><i class="ace-icon fa fa-close"></i></a>'+
								'</div>'
							'</div>';

		return secondaryHTML;
	}

	TMDchoose.init.prototype.getValues = function(){
		return this.values;
	}

	//表格基础操作增加删除
	var TMDtableBase = function(options){
		var that = this;
		this.options = {
			table:'',//操作的表格名称
			plus:'',//插入按钮类名
			plusHTML:'',//插入的元素
			plusinit:false,//插入元素后执行
			edit:'',//绑定编辑的类名
			editAdd:false,//编辑开启状态函数
			editRem:false,//编辑取消状态函数
			del:'',//绑定删除的类名
			delFun:false,//删除函数
			Other:false,//[{class:'绑定子元素类名'.fun:'绑定的类名'}]
		}
		if(options){
			for(var item in options){
				this.options[item] = options[item]
			}
		}
		try{
			console.log(that.options.table)
			$(that).on("click",that.options.plus,function(){
				$(that).find(that.options.table).find('tbody').append(that.options.plusHTML);
				that.options.plusinit?that.options.plusinit(this,that):null;
			})
		$(that).delegate(that.options.edit,"click",function(){
			if($(this).hasClass("active")){
				$(this).removeClass("active");
				that.options.editAdd?that.options.editAdd(this,that):null;;
			}else{
				$(this).addClass("active");
				that.options.editRem?that.options.editRem(this,that):null;;
			}
		})
		$(that).delegate(that.options.del,"click",function(){
			that.options.delFun?that.options.delFun(this,that):null;;
		})
		if(that.options.Other){
			for(var i = 0;i < that.options.Other.length; i++){
				(function(i){
					var index = i;
				$(that).delegate(that.options.Other[index]['class'],"click",function(){
					that.options.Other[index]['fun'](this,that);
				})
				})(i)
			}
		}

		}catch(e){
			alert(e)
		}
	}

	//针对元素的绑定事件防止污染到其他同类名元素
	var TMDbind = function(options){
		var self = this;
		$(self).unbind();
		for(var l = 0;l<self.length; l++){
			(function(self){
				for(var i = 0;i < options.length; i++){
				(function(i,self){
					var index = options[i];
					$(self).delegate(index['class'],index['event'],function(){
						index['fun'](this,self);
					})
				})(i,self)
			}
		})(self[l])
		}
	}

	//获取元素对象的克隆文本
	var TMDhtml = function(){
		var dom = $('<div></div>');
		dom.html(this);
		var res =dom.html();
		return res;
	}

	//表单交互
	var  TMDhover = function(bg,color){
		var self =this;
		for(var l = 0;l<self.length; l++){
			(function(self){
				var w = $(self).width();
				var h = $(self).height();
				var th = $(self).find('thead').height();
				var parentDom = $("<div class='TMDhoverbox' style='position:relative;width:100%;height:"+h+"px;'></div>");
				var hoverAarossDom = $("<div style='z-index:1;pointer-events:none;opacity:.5;width:100%;position:absolute;left:0;background-color:"+bg+";'></div>");
				var hoverVerticalDom = $("<div style='z-index:1;pointer-events:none;opacity:.5;height:"+(h-th+1)+"px;position:absolute;top:0px;background-color:"+bg+";'></div>");
				$(self).css({
					'position':'relative',
					'z-index':99,
				})
				$(self).parent().append(parentDom);
				parentDom.append($(self));
				parentDom.append(hoverAarossDom);
				parentDom.append(hoverVerticalDom);
				$(self).find('td').on({
					'mouseover':function(){
						var width = $(this).outerWidth();
						var height = $(this).outerHeight();
						var top =  $(this).position().top + parseInt($(this).parent().css("border-top"));
						var left =  $(this).position().left + parseInt($(this).css("border-left"));
						hoverAarossDom.css('height',height+'px');
						hoverVerticalDom.css('width',(width)+'px');
						hoverAarossDom.css('top',top+'px');
						hoverVerticalDom.css('left',left+'px')
					},
					'mouseout':function(){

					}
				})
			})(self[l])
		}
		// var TMDhoverClass = 'TMDhover'+new Date().getTime();
		// $('<style class="TMDhover"> .'+TMDhoverClass+',.'+TMDhoverClass+' td{color:'+color+';background-color:'+bg+'}</style>').appendTo('body')
		// $(this).find('td').on({
		// 	'mouseover':function(){
		// 	var i = $(this).parent().find('td').length-$(this).parent().find('td').index(this);
		// 	$(this).parent().addClass(TMDhoverClass);
		// 	$(this).parents('table').find('tr').each(function(){
		// 		$(this).children('td').last(i).addClass(TMDhoverClass);
		// 	})
		// 	},
		// 	'mouseout':function(){
		// 	$(this).parent().removeClass(TMDhoverClass);
		// 	$(this).parents('table').find('td').removeClass(TMDhoverClass);
		// 	},
		// })
	}


	$.fn.extend({
		TMDdualListbox : TMDdualListbox,
		TMDfileImg: TMDfileImg,
		TMDtabbable : TMDtabbable,
		TMDchoose : TMDchoose,
		TMDtableBase: TMDtableBase,
		TMDbind : TMDbind,
		TMDhtml : TMDhtml,
		TMDhover : TMDhover,
	})
	 
}(jQuery));