var ctx;

// $(document).ready(function () {
$(function () {
    // https://stackoverflow.com/a/5064235/548473
    ctx = {
        ajaxUrl: "ajax/meals/",
        datatableApi: $("#datatable").DataTable({
            "paging": false,
            "info": true,
            "columns": [
                {
                    "data": "dateTime"
                },
                {
                    "data": "description"
                },
                {
                    "data": "calories"
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
                    "desc"
                ]
            ]
        }), updateTable: updateFilteredTable
    };
    makeEditable();
});

function updateFilteredTable() {
    $.get(ctx.ajaxUrl + "filter", $("#filter").serialize(), update)
}

function clearFilter() {
    $("#filter")[0].reset();
    $.get(ctx.ajaxUrl, update);
}