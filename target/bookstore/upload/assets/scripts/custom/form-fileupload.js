

var FormFileUpload = function () {


    return {
        //main function to initiate the module
        init: function () {

            // Initialize the jQuery File Upload widget:
            $('#fileupload').fileupload({
                disableImageResize: false,
                autoUpload: false,
                // Uncomment the following to send cross-domain cookies:
                //xhrFields: {withCredentials: true},
                url: 'http://localhost:8090/bookstore/UploadServlet?method=upload',

                // always: function (e, data) {
                //     $(this).removeClass('fileupload-processing');
                // },
                // done: function (e, data) {
                //     $(this).removeClass('fileupload-processing');
                //     console.log(data);
                //     if (data.files[0].error!=null){
                //         $("#errT").css('visibility','visible');
                //         $("#errT>span.msg").html(data.files[0].error);
                //     }else {
                //         $(function () {
                //             swal({
                //                 title: "上传成功",
                //                 text: "可点击右侧查看结果按钮咯",
                //                 type: "success",
                //                 timer: 2000,
                //                 showConfirmButton: false
                //             });
                //             setTimeout(function() {
                //
                //             }, 2000);
                //         })
                //         $("#errT").css('visibility','hidden');
                //         $("#errT>span.msg").html("");
                //     }
                //     // $(this).fileupload('option', 'done')
                //     //     .call(this, $.Event('done'), {result: data});
                // },
            });

            // Enable iframe cross-domain access via redirect option:
            $('#fileupload').fileupload(
                'option',
                'redirect',
                window.location.href.replace(
                    /\/[^\/]*$/,
                    '/cors/result.html?%s'
                )
            );

            // Demo settings:
            $('#fileupload').fileupload('option', {
                url: $('#fileupload').fileupload('option', 'url'),
                // Enable image resizing, except for Android and Opera,
                // which actually support image resizing, but fail to
                // send Blob objects via XHR requests:
                disableImageResize: /Android(?!.*Chrome)|Opera/.test(window.navigator.userAgent),
                maxFileSize: 500000000,
                // acceptFileTypes: /(\.|\/)(gif|jpe?g|png)$/i
                acceptFileTypes: /(\.|\/)(xlsx|xlsm|xlsb|xls)$/i
            });

            // Upload server status check for browsers with CORS support:
            // if ($.support.cors) {
            //     $.ajax({
            //         url: 'http://localhost:8090/bookstore/UploadServlet?method=upload',
            //         type: 'HEAD'
            //     }).fail(function () {
            //         $('<div class="alert alert-danger"/>')
            //             .text('Upload server currently unavailable - ' +
            //                     new Date())
            //             .appendTo('#fileupload');
            //     });
            // }

            // Load & display existing files:
            // $('#fileupload').addClass('fileupload-processing');
            // $.ajax({
            //     // Uncomment the following to send cross-domain cookies:
            //     //xhrFields: {withCredentials: true},
            //     url: $('#fileupload').fileupload('option', 'url'),
            //     dataType: 'json',
            //     context: $('#fileupload')[0]
            // }).always(function () {
            //     $(this).removeClass('fileupload-processing');
            // }).done(function (result) {
            //     $(this).fileupload('option', 'done')
            //         .call(this, $.Event('done'), {result: result});
            // });
        }

    };

}();