function Table (jsondata) {
	this.result = function () {
		if(jsondata!=null) {
			var tbl = $j('<table></table>');
			if(jsondata.name) $j(tbl).attr('name',jsondata.name);
			if (jsondata.idProperty)	$j(tbl).attr('id',jsondata.idProperty);
			if (jsondata.className)	$j(tbl).attr('class',jsondata.className); 
		     if(jsondata.style)$j(tbl).attr('style',jsondata.style);
			/* Create Header Part */
			if(jsondata.colHeaders) { 
				var theader = $j('<thead></thead>');
				var row = $j('<tr></tr>');		
				$j(jsondata.colHeaders).each(function(index,header) {
					var colHead = $j('<th></th>');
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
						$j(header.content).each(function(index,headercontent) {
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
			var tbody = $j('<tbody></tbody>');
		/*	$j(jsondata.data).each(function(index, element) {
				row = $j('<tr></tr>');
				$j(jsondata.colHeaders).each(function(index,data) {
					var colHead = $j('<td>'+element[data.name]+'</td>');
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
