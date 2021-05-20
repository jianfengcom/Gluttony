/**
 * support mutiple upload 依赖jq、jq.cookie example: new Uploader({ 'url':
 * 'http://dev65.pconline.com.cn:8080/uploadcenter/upload_quick.jsp', // upload
 * url 'commands': '406', 'success': 'back', 'params': {application:
 * 'houselib'}, // upload url params 'files': [{'id': 'file1', 'params':
 * {'imageId': 'image1'}}, {'id': 'file2', 'params': {'imageId': 'image2'}}]
 * }).upload();
 * 
 * function back(data, params) { // params: circle params var url =
 * data.files[0].url; $("#" + params.imageId).attr('src', url); }
 */

var _uploader = {
	/**
	 * create frame to get the return result
	 * 
	 * @param fileElementId
	 * @return
	 */
	createFrame : function(fileElementId, uri) {
		var frameId = "upload_frame_id_" + fileElementId;
		var frame;
		try { // for I.E.
			frame = document.createElement('<iframe name="' + frameId + '">');
		} catch (ex) { //for other browsers, an exception will be thrown
			frame = document.createElement('iframe');
			frame.name = frameId;
		}
		frame.id = frameId;
		
		if (typeof uri == 'boolean') {
			frame.src = 'javascript:false';
		} else if (typeof uri == 'string') {
			frame.src = uri;
		}
		frame.style.position = 'absolute';
		frame.style.top = '-1000px';
		frame.style.left = '-1000px';

		document.body.appendChild(frame);
		return frame;
	},
	/**
	 * create form to submit file data
	 * 
	 * @param fileElementId
	 * @return
	 */
	createForm : function(fileElementId) {
		var formId = 'upload_form_id_' + fileElementId;
		var fileId = 'upload_file_id_' + fileElementId;
		var form = $('<form  action="" method="POST" name="' + formId
				+ '" id="' + formId + '" enctype="multipart/form-data"></form>');

		// clone the file element to the form
		var oldElement = $('#' + fileElementId);
		var newElement = $(oldElement).clone(); // to solute the ie bug clone
												// can't get the content, only
												// get the properties
		$(oldElement).attr('id', fileId);
		$(oldElement).before(newElement);
		$(oldElement).appendTo(form);

		$(form).css('position', 'absolute');
		$(form).css('top', '-1200px');
		$(form).css('left', '-1200px');
		$(form).appendTo('body');
		return form;
	},
	success : function(data, params) {// 根据需求实现覆盖
	},
	/**
	 * upload
	 * 
	 * @param url
	 *            upload url
	 * @param params
	 *            json object
	 * @param commands
	 *            command
	 * @param callback
	 *            callback
	 * @param fileElementId
	 *            fileupload url, params, commands, callback, fileElementId
	 */
	upload : function() {
		if (!this.url) {
			alert('上传url未指定');
			return;
		}

		if (this.url.lastIndexOf('?') == -1) {
			this.url += "?";
		}

		var reqData = {// 请求参数
			time : Math.random(),
			windowname : 1,
			fileType : this.fileType || 'Picture',
			application : 'wallpaper',
			keepSrc : this.keepSrc || 'no'
			//common_session_id : $.cookie('common_session_id')
		};

		if (this.params) {
			$.extend(reqData, this.params); // 合并参数
		}

		for ( var key in reqData) {
			this.url += "&" + key + "=" + reqData[key];
		}

		if (this.commands) {
			this.commands = this.commands.split(',');
			for ( var i = 0; i < this.commands.length; i++) {
				$.trim(this.commands[i]);
				this.url += '&command=' + $.trim(this.commands[i]);
			}
		}

		if (!this.files || this.files.length < 1) {
			alert("系统调用错误，请联系技术人员。");
			return;
		}

		for ( var index in this.files) {
			var file = this.files[index];
			if (!file || !file.id) { // 检查参数
				break;
			}
			this.submit(file.id, this.success, file.params);
		}
	},
	/**
	 * submit
	 * 
	 * @param fileElementId
	 * @param success
	 *            callback
	 * @param params
	 *            callback params
	 */
	submit : function(fileElementId, success, params) {
		// create form & frame
		var form = this.createForm(fileElementId);
		var frame = this.createFrame(fileElementId, false);

		// add onload event
		$(frame).load(function() {
			if (this.stats == 1) {
				var data = frame.contentWindow.name;
				// clear
				$(frame).unload();
				$(frame).remove();
				$(form).remove();
				// 转换数据为对象
				eval('var obj = ' + data);

				if (obj.retCode == 0) {
					// 上传成功,交给回调函数处理
					eval(success + "(" + data + ", params)");
				} else if (obj.retCode == 12) {
					alert('上传失败,请先登录太平洋通行证。');
				} else if (obj.retCode == 14) {
					alert('图片大小超过限制。');
				} else {
					alert('上传失败,请联系技术人员,错误编号: ' + obj.retCode);
				}

				this.stats = 0;
			} else {
				this.stats = 1;
				// frame.contentWindow.location = document.URL;
				frame.contentWindow.location = 'blank'; // 空文件,转域
			}
		});
		// modify form & submit data
		$(form).attr('action', this.url);
		$(form).attr('method', 'POST');
		$(form).attr('target', frame.id);
		$(form).submit();
	}
};

var Uploader = function(settings) {
	$.extend(this, settings);
	this.stats = 0;
};

Uploader.prototype = _uploader;