$j(document).ready(function(){
	 $j('#btnBlock').die('click').click(function(){
		 removeErrors();
		var blockUI = new BlockUIStartup();			
		blockUI.init();  
	});
	 $j('#btnBedType').die('click').click(function(){
		 removeErrors();
		var bedTypeUI = new BedTypeUIStartup();			
		bedTypeUI.init(); 
		 
	});
	 $j('#btnRoomType').die('click').click(function(){
		 removeErrors();
		var roomTypeUI = new RoomTypeUIStartup();			
		roomTypeUI.init(); 
		 
	});
	 $j('#btnDepartment').die('click').click(function(){
		 removeErrors();
		var DepartmentUI = new DepartmentUIStartup();			
		DepartmentUI.init(); 
		 
	});
	 $j('#btnRoom').die('click').click(function(){
		 removeErrors();
		var roomUI = new RoomUIStartup();			
		roomUI.init(); 
		 
	});
	 $j('#btnTax').die('click').click(function(){
		 removeErrors();
		var taxUI = new TaxUIStartup();			
		taxUI.init(); 	 
	});
	 $j('#btnItem').die('click').click(function(){
		 removeErrors();
		var itemUI = new ItemUIStartup();			
		itemUI.init(); 
		 
	});
	 $j('#btnService').die('click').click(function(){
		 removeErrors();
		var supportUI = new SupportUIStartup();			
		supportUI.init(); 
		 
	});
	  $j('#btnBed').die('click').click(function(){
		 removeErrors();
		var bedUI = new BedUIStartup();			
		bedUI.init(); 
		 
	});
	  $j('#btnBill').die('click').click(function(){
		 removeErrors();
		var billUI = new BillUIStartup();			
		billUI.init(); 
		 
	});
	 $j('#btnDiscount').die('click').click(function(){
		 removeErrors();
		var discountUI = new DiscountUIStartup();			
		discountUI.init(); 
		 
	});
	 $j('#btnAdvance').die('click').click(function(){
		 removeErrors();
		var advanceUI = new AdvanceUIStartup();			
		advanceUI.init(); 
		 
	});
	 $j('#btnHead').die('click').click(function(){
		 removeErrors();
		var headUI = new HeadUIStartup();			
		headUI.init(); 
		 
	});
	 $j('#btnSettings').die('click').click(function(){
			removeErrors();
			var settingUI = new SettingUIStartup();			
			settingUI.init(); 	 
		});
		
		$j("#btnQuestionnaire").die('click').live("click",function() {
			removeErrors();
			var questionnaireUI = new QuestionnaireStartUp();			
			questionnaireUI.init(); 
		 
		
	});
});
