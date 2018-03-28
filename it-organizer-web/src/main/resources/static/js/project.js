function addProjectNewTab() {
    var nextTab = $('#tabs li').size()+1;

    // create the tab
    $('<li><a href="#tab'+nextTab+'" data-toggle="tab">New Project</a></li>').appendTo('#tabs');

    // create the tab content
    $('<div class="tab-pane" id="tab'+nextTab+'">New Project content</div>').appendTo('.tab-content');

    // make the new tab active
    $('#tabs a:last').tab('show');
};