$(function () {

    $("#fileForm").on("submit", function (e) {
        e.preventDefault();

        var formData = new FormData(this);

        $.ajax({
            type: "POST",
            url: "http://localhost:8080/spring4web/file",
            data: formData,
            contentType: false,
            processData: false,
            xhr: function () {
                var myXhr = $.ajaxSettings.xhr();
                if (myXhr.upload) {
                    myXhr.upload.addEventListener('progress', progressHandling, false);
                }
                return myXhr;
            },
            // this xhr: for bind event listener to monitor file upload progress
            success: function (data) {
                console.log(data);
            },
            error: function (error) {
                console.error(error);
            }
        });

        function progressHandling(e) {
            console.log(e);
        }
    });
});