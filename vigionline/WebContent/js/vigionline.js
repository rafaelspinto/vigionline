function AppendToMenu(menu_id, url, name)
{
	$('#menu ul #'+menu_id).append(
		    $('<li>').append(
		        $('<a>').attr('href',url).append(
		            $('<span>').attr('class', 'tab').append(name)
		))); 
}

function ValidateForm(form_id, fld, v_fld)
{
	$("#"+form_id).submit(function(e)
			{
				var pwd = $("#"+fld).val();
				var cpwd = 	$("#"+v_fld).val();
				if( pwd != cpwd)
				{
					e.preventDefault();
					$("#"+v_fld).attr('class','error');
					var div = $("#cpwd").parents("div.control-group");
					div.removeClass("success");
					div.addClass("error");
				}
				else
					this.submit();
			});
}