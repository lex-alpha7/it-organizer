function addNewTab(loadURL, name) {
    var nextTab = $('#tabs li').size()+1;

    // create the tab
    $('<li><a href="#tab' + nextTab + '" data-toggle="tab">' + name + '</a></li>').appendTo('#tabs');

    // create the tab content
    $('<div class="tab-pane" id="tab' + nextTab + '"></div>').appendTo('.tab-content');

    $("#tab"+nextTab+"").load(loadURL);

    // make the new tab active
    $('#tabs a:last').tab('show');
};