function AppendToMenu(menu_id, url, name)
{
	$('#'+menu_id).append(
			$('<a>').attr('href',url).append(
		            $('<button><i class="icon-edit"></i>').attr('class', 'label label-info').append(name)
		)); 
}

function makeDeleteModelForm(menu_id, action, id, value, label)
{
	$form = $('<form>');
	($form).attr('action', action);
	($form).attr('method', "POST");
	($form).append(	$('<input>').attr('type', 'hidden').attr('name', id).attr('value',value));
	
	($form).append(	$('<button>').attr('type', 'submit').attr('class', "label label-important").append('<i class="icon-remove"></i>'+label));
	$('#'+menu_id).append($form);
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