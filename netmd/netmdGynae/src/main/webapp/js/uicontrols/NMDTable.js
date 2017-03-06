function table (jsondata) {
	this.result = function () {
		if(jsondata!=null) {
			var tbl = jQuery('<table></table>');
			if(jsondata.name) jQuery(tbl).attr('name',jsondata.name);
			if (jsondata.idProperty)	jQuery(tbl).attr('id',jsondata.idProperty);
			if (jsondata.className)	jQuery(tbl).attr('class',jsondata.className); 
		     if(jsondata.style)jQuery(tbl).attr('style',jsondata.style);
			/* Create Header Part */
			if(jsondata.colHeaders) { 
				var theader = jQuery('<thead></thead>');
				var row = jQuery('<tr></tr>');		
				jQuery(jsondata.colHeaders).each(function(index,header) {
					var colHead = jQuery('<th></th>');
					if(header.className) colHead.attr('class',header.className);
					if(header.style) colHead.attr('style',header.style);
					if(header.id) colHead.attr('id',header.id);
					var data;
					if(header.name)	data=header.name;
					if(header.image){
					 data+= ' (<img src ="' + header.image + '" />)';
					}
					var innerdata="";
					if(header.content) {
						jQuery(header.content).each(function(index,headercontent) {
							innerdata = new datalabel(headercontent).result;
						});
					}
					colHead.html(data);
					colHead.append(innerdata);
					row.append(colHead);
				});
				theader.append(row);
				tbl.append(theader);
				/* Header Part Created*/
			}
        
			/* Create data Part */
			var tbody = jQuery('<tbody></tbody>');
		/*	jQuery(jsondata.data).each(function(index, element) {
				row = jQuery('<tr></tr>');
				jQuery(jsondata.colHeaders).each(function(index,data) {
					var colHead = jQuery('<td>'+element[data.name]+'</td>');
					row.append(colHead);
				});
				tbody.append(row);
			});*/
			tbl.append(tbody);
			/* Data Added to the Table */
		}
		return tbl;
	};
}
