var ctx;

// $(document).ready(function () {
$(function () {
    // https://stackoverflow.com/a/5064235/548473
    ctx = {
        ajaxUrl: "admin/users/",
        datatableApi: $("#datatable").DataTable({
            "paging": false,
            "info": true,
            "columns": [
                {
                    "data": "name"
                },
                {
                    "data": "email"
                },
                {
                    "data": "roles"
                },
                {
                    "data": "enabled"
                },
                {
                    "data": "registered"
                },
                {
                    "defaultContent": "Edit",
                    "orderable": false
                },
                {
                    "defaultContent": "Delete",
                    "orderable": false
                }
            ],
            "order": [
                [
                    0,
                    "asc"
                ]
            ]
        }), updateTable: function () {
            $.get(ctx.ajaxUrl, update)
        }
    };
    makeEditable();
});

function enable(checkbox, id) {
    var enabled = checkbox.is(":checked");

    $.post(ctx.ajaxUrl + id, "enabled=" + enabled, function () {
        checkbox.closest("tr").attr("data-userEnabled", enabled);
        successNoty(enabled ? "Пользователь включен" : "Пользователь отключен");
    }).fail(function () {
        $(checkbox).prop("checked", !enabled);
    });
}