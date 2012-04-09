function AppendToMenu(menu_id, url, name)
{
	$('#menu ul #'+menu_id).append(
		    $('<li>').append(
		        $('<a>').attr('href',url).append(
		            $('<span>').attr('class', 'tab').append(name)
		))); 
}