function ClassLoader()
{ /*common resource */
    $j.getScript("/netmd/js/netmd/resource/PageHandler.js").done(function (script, textStatus)
    {}).fail(function (xhr, status, exception)
    {
        alert("pageHandler" + xhr.status + exception);
    })
    $j.getScript("/netmd/js/netmd/resource/Constants.js").done(function (script, textStatus)
    {}).fail(function (xhr, status, exception)
    {
        alert("constants" + xhr.status + exception);
    })

    $j.getScript("/netmd/js/netmd/resource/PageToolBarProcessor.js").done(function (script, textStatus)
    {}).fail(function (xhr, status, exception)
    {
        alert("pageToolBarProcessor" + xhr.status + exception);
    })

    $j.getScript("/netmd/js/netmd/resource/FilterToolBarProcessor.js").done(function (script, textStatus)
    {}).fail(function (xhr, status, exception)
    {
        alert(xhr.status + exception);
    })
    $j.getScript("/netmd/js/netmd/resource/DataTableProcessor.js").done(function (script, textStatus)
    {}).fail(function (xhr, status, exception)
    {
        alert("dataTableProcessor" + xhr.status + exception);
    })
	$j.getScript("/netmd/js/netmd/resource/BillPrintProcessor.js").done(function (script, textStatus)
    {}).fail(function (xhr, status, exception)
    {
        alert("BillPrintProcessor" + xhr.status + exception);
    })
    $j.getScript("/netmd/js/netmd/resource/CommonMethodInvoker.js").done(function (script, textStatus)
    {}).fail(function (xhr, status, exception)
    {
        alert("commonMethodInvoker" + xhr.status + exception);
    })
    $j.getScript("/netmd/js/netmd/resource/NetmdMethodInvoker.js").done(function (script, textStatus)
    {}).fail(function (xhr, status, exception)
    {
        alert(xhr.status + exception);
    })

    $j.getScript("/netmd/js/netmd/resource/Validator.js").done(function (script, textStatus)
    {}).fail(function (xhr, status, exception)
    {
        alert("validator" + xhr.status + exception);
    })
    $j.getScript("/netmd/js/netmd/resource/DataTableNavigator.js").done(function (script, textStatus)
    {}).fail(function (xhr, status, exception)
    {
        alert(xhr.status + exception);
    })
    $j.getScript("/netmd/js/netmd/resource/ServerUrlProcessor.js").done(function (script, textStatus)
    {}).fail(function (xhr, status, exception)
    {
        alert("serverUrlProcessor" + xhr.status + exception);
    })
	
	
 /* Settings ClassLoader*/
    $j.getScript("/netmd/js/netmd/ui/setting/SettingClassLoader.js").done(function(script,textStatus){
        }).fail(function (xhr, status, exception)
        {
        alert("SettingClassLoader" + xhr.status + exception);
        })
		
		
    /*--dto----------*/
    $j.getScript("/netmd/js/netmd/dto/ErrorDTO.js").done(function (script, textStatus)
    {}).fail(function (xhr, status, exception)
    {
        alert(xhr + exception);
    })
    $j.getScript("/netmd/js/netmd/dto/ErrorMessageDTO.js").done(function (script, textStatus)
    {})
    $j.getScript("/netmd/js/netmd/dto/ExpressionDTO.js").done(function (script, textStatus)
    {}).fail(function (xhr, status, exception)
    {
        alert(xhr + exception);
    })
    $j.getScript("/netmd/js/netmd/dto/ExpressionListDTO.js").done(function (script, textStatus)
    {}).fail(function (xhr, status, exception)
    {
        alert(xhr + exception);
    })
    $j.getScript("/netmd/js/netmd/dto/FilterDTO.js").done(function (script, textStatus)
    {}).fail(function (xhr, status, exception)
    {
        alert(xhr + exception);
    })
	 $j.getScript("/netmd/js/netmd/dto/PatientDTO.js").done(function (script, textStatus)
    {}).fail(function (xhr, status, exception)
    {
        alert(xhr + exception);
    })
    $j.getScript("/netmd/js/netmd/dto/BlockDTO.js").done(function (script, textStatus)
    {}).fail(function (xhr, status, exception)
    {
        alert(xhr + exception);
    })
    $j.getScript("/netmd/js/netmd/dto/DepartmentDTO.js").done(function (script, textStatus)
    {}).fail(function (xhr, status, exception)
    {
        alert(xhr + exception);
    })

    $j.getScript("/netmd/js/netmd/dto/BedTypeDTO.js").done(function (script, textStatus)
    {}).fail(function (xhr, status, exception)
    {
        alert(xhr + exception);
    })
    $j.getScript("/netmd/js/netmd/dto/RoomTypeDTO.js").done(function (script, textStatus)
    {}).fail(function (xhr, status, exception)
    {
        alert(xhr + exception);
    })
    $j.getScript("/netmd/js/netmd/dto/RoomDTO.js").done(function (script, textStatus)
    {}).fail(function (xhr, status, exception)
    {
        alert(xhr + exception);
    })
    $j.getScript("/netmd/js/netmd/dto/TaxDTO.js").done(function (script, textStatus)
    {}).fail(function (xhr, status, exception)
    {
        alert(xhr + exception);
    })
	 $j.getScript("/netmd/js/netmd/dto/SupportDTO.js").done(function (script, textStatus)
    {}).fail(function (xhr, status, exception)
    {
        alert("SupportDTO"+xhr + exception);
    })
	 $j.getScript("/netmd/js/netmd/dto/BedDTO.js").done(function (script, textStatus)
    {}).fail(function (xhr, status, exception)
    {
        alert("BedDTO"+xhr + exception);
    })
	$j.getScript("/netmd/js/netmd/dto/ItemDTO.js").done(function (script, textStatus)
    {}).fail(function (xhr, status, exception)
    {
        alert("ItemDTO"+xhr + exception);
    })
	$j.getScript("/netmd/js/netmd/dto/DiscountDTO.js").done(function (script, textStatus)
    {}).fail(function (xhr, status, exception)
    {
        alert("DiscountDTO"+xhr + exception);
    })
	$j.getScript("/netmd/js/netmd/dto/BillItemDTO.js").done(function (script, textStatus)
    {}).fail(function (xhr, status, exception)
    {
        alert("BillItemDTO"+xhr + exception);
    })
	$j.getScript("/netmd/js/netmd/dto/BillDTO.js").done(function (script, textStatus)
    {}).fail(function (xhr, status, exception)
    {
        alert("BillDTO"+xhr + exception);
    })
	$j.getScript("/netmd/js/netmd/dto/BillSupportDTO.js").done(function (script, textStatus)
    {}).fail(function (xhr, status, exception)
    {
        alert("BillSupportDTO"+xhr + exception);
    })
	$j.getScript("/netmd/js/netmd/dto/BillBedDTO.js").done(function (script, textStatus)
    {}).fail(function (xhr, status, exception)
    {
        alert("BillBedDTO"+xhr + exception);
    })
	$j.getScript("/netmd/js/netmd/dto/NetBillDiscountDTO.js").done(function (script, textStatus)
    {}).fail(function (xhr, status, exception)
    {
        alert("NetBillDiscountDTO"+xhr + exception);
    })
	$j.getScript("/netmd/js/netmd/dto/BillDiscountDTO.js").done(function (script, textStatus)
    {}).fail(function (xhr, status, exception)
    {
        alert("BillDiscountDTO"+xhr + exception);
    })
	$j.getScript("/netmd/js/netmd/dto/BillPaymentDTO.js").done(function (script, textStatus)
    {}).fail(function (xhr, status, exception)
    {
        alert("BillPaymentDTO"+xhr + exception);
    })
	$j.getScript("/netmd/js/netmd/dto/BillDiscountDetailDTO.js").done(function (script, textStatus)
    {}).fail(function (xhr, status, exception)
    {
        alert("DiscountDetailDTO"+xhr + exception);
    })
	
	$j.getScript("/netmd/js/netmd/dto/BillPatientHeaderDTO.js").done(function (script, textStatus)
    {}).fail(function (xhr, status, exception)
    {
        alert("BillPatientHeaderDTO"+xhr + exception);
    })
	$j.getScript("/netmd/js/netmd/dto/BillItemListDTO.js").done(function (script, textStatus)
    {}).fail(function (xhr, status, exception)
    {
        alert("BillItemListDTO"+xhr + exception);
    })
	$j.getScript("/netmd/js/netmd/dto/BillStatusDTO.js").done(function (script, textStatus)
    {}).fail(function (xhr, status, exception)
    {
        alert("BillStatusDTO"+xhr + exception);
    })
	$j.getScript("/netmd/js/netmd/dto/BillStatusRequestDTO.js").done(function (script, textStatus)
    {}).fail(function (xhr, status, exception)
    {
        alert("BillStatusRequestDTO"+xhr + exception);
    })
	$j.getScript("/netmd/js/netmd/dto/AdvanceDTO.js").done(function (script, textStatus)
    {}).fail(function (xhr, status, exception)
    {
        alert("AdvanceDTO"+xhr + exception);
    })
	$j.getScript("/netmd/js/netmd/dto/HeadDTO.js").done(function (script, textStatus)
    {}).fail(function (xhr, status, exception)
    {
        alert("HeadDTO"+xhr + exception);
    })
	 $j.getScript("/netmd/js/netmd/dto/ExpenseDTO.js").done(function (script, textStatus)
    {}).fail(function (xhr, status, exception)
    {
        alert("ExpenseDTO"+xhr + exception);
    })   

    $j.getScript("/netmd/js/netmd/dto/SettingDTO.js").done(function (script, textStatus)
    {}).fail(function (xhr, status, exception)
    {
        alert("SettingDTO" + xhr.status + exception);
    })
	
	 $j.getScript("/netmd/js/netmd/dto/SettingListDTO.js").done(function (script, textStatus)
    {}).fail(function (xhr, status, exception)
    {
        alert("SettingListDTO" + xhr.status + exception);
    })
    	$j.getScript("/netmd/js/netmd/dto/AdvanceDTO.js").done(function (script, textStatus)
    {}).fail(function (xhr, status, exception)
    {
        alert("AdvanceDTO"+xhr + exception);
    })
	$j.getScript("/netmd/js/netmd/dto/CaseDTO.js").done(function (script, textStatus)
    {}).fail(function (xhr, status, exception)
    {
        alert("CaseDTO"+xhr + exception);
    })
	$j.getScript("/netmd/js/netmd/dto/AnswerDTO.js").done(function (script, textStatus)
    {}).fail(function (xhr, status, exception)
    {
        alert("AnswerDTO"+xhr + exception);
    })
	$j.getScript("/netmd/js/netmd/dto/QuestionAnswerDTO.js").done(function (script, textStatus)
    {}).fail(function (xhr, status, exception)
    {
        alert("QuestionAnswerDTO"+xhr + exception);
    })
	$j.getScript("/netmd/js/netmd/dto/MedicalRecordDTO.js").done(function (script, textStatus)
    {}).fail(function (xhr, status, exception)
    {
        alert("MedicalRecordDTO"+xhr + exception);
    })
	$j.getScript("/netmd/js/netmd/dto/AutoSaveDTO.js").done(function (script, textStatus)
    {}).fail(function (xhr, status, exception)
    {
        alert("AutoSaveDTO"+xhr + exception);
    })
   /*Patient UI Pages */
	$j.getScript("/netmd/js/netmd/ui/Patient/PatientUIStartup.js").done(function (script, textStatus)
    {}).fail(function (xhr, status, exception)
    {
        alert("PatientUIStartup" + xhr.status + exception);
    })
    $j.getScript("/netmd/js/netmd/ui/Patient/ViewPatientUI.js").done(function (script, textStatus)
    {}).fail(function (xhr, status, exception)
    {
        alert("ViewPatientUI"+xhr.status + exception);
    })
    $j.getScript("/netmd/js/netmd/ui/Patient/ViewPatientPTB.js").done(function (script, textStatus)
    {}).fail(function (xhr, status, exception)
    {
        alert("ViewPatientPTB"+xhr.status + exception);
    })
    $j.getScript("/netmd/js/netmd/ui/Patient/NewPatientUI.js").done(function (script, textStatus)
    {}).fail(function (xhr, status, exception)
    {
        alert("NewPatientUI" + xhr.status + exception);
    })
    
  /* Block UI Pages */
    $j.getScript("/netmd/js/netmd/ui/Block/BlockUIStartup.js").done(function (script, textStatus)
    {}).fail(function (xhr, status, exception)
    {
        alert("blockUIStartup" + xhr.status + exception);
    })
    $j.getScript("/netmd/js/netmd/ui/Block/ViewBlockUI.js").done(function (script, textStatus)
    {}).fail(function (xhr, status, exception)
    {
        alert("ViewBlockUI"+xhr.status + exception);
    })
    $j.getScript("/netmd/js/netmd/ui/Block/ViewBlockPTB.js").done(function (script, textStatus)
    {}).fail(function (xhr, status, exception)
    {
        alert("ViewBlockPTB"+xhr.status + exception);
    })
    $j.getScript("/netmd/js/netmd/ui/Block/NewBlockUI.js").done(function (script, textStatus)
    {}).fail(function (xhr, status, exception)
    {
        alert("NewBlockUI" + xhr.status + exception);
    }) /*------BedType UI Pages-----------------------------------*/
    $j.getScript("/netmd/js/netmd/ui/BedType/BedTypeUIStartup.js").done(function (script, textStatus)
    {}).fail(function (xhr, status, exception)
    {
        alert("bedTypeUIStartup" + xhr.status + exception);
    })
    $j.getScript("/netmd/js/netmd/ui/BedType/ViewBedTypeUI.js").done(function (script, textStatus)
    {}).fail(function (xhr, status, exception)
    {
        alert("ViewBedTypeUI" + xhr.status + exception);
    })
    $j.getScript("/netmd/js/netmd/ui/BedType/ViewBedTypePTB.js").done(function (script, textStatus)
    {}).fail(function (xhr, status, exception)
    {
        alert("ViewBedTypePTB" + xhr.status + exception);
    })
    $j.getScript("/netmd/js/netmd/ui/BedType/NewBedTypeUI.js").done(function (script, textStatus)
    {}).fail(function (xhr, status, exception)
    {
        alert("NewBedTypeUI" + xhr.status + exception);
    }) /*-------Room Type-------------------------------*/
    $j.getScript("/netmd/js/netmd/ui/RoomType/RoomTypeUIStartup.js").done(function (script, textStatus)
    {}).fail(function (xhr, status, exception)
    {
        alert("RoomTypeUIStartup" + xhr.status + exception);
    })
    $j.getScript("/netmd/js/netmd/ui/RoomType/ViewRoomTypeUI.js").done(function (script, textStatus)
    {}).fail(function (xhr, status, exception)
    {
        alert("ViewRoomTypeUI" + xhr.status + exception);
    })
    $j.getScript("/netmd/js/netmd/ui/RoomType/ViewRoomTypePTB.js").done(function (script, textStatus)
    {}).fail(function (xhr, status, exception)
    {
        alert("ViewRoomTypePTB" + xhr.status + exception);
    })
    $j.getScript("/netmd/js/netmd/ui/RoomType/NewRoomTypeUI.js").done(function (script, textStatus)
    {}).fail(function (xhr, status, exception)
    {
        alert("NewRoomTypeUI" + xhr.status + exception);
    }) /*-------Room -------------------------------*/
    $j.getScript("/netmd/js/netmd/ui/Room/RoomUIStartup.js").done(function (script, textStatus)
    {}).fail(function (xhr, status, exception)
    {
        alert("RoomTypeUIStartup" + xhr.status + exception);
    })
    $j.getScript("/netmd/js/netmd/ui/Room/ViewRoomUI.js").done(function (script, textStatus)
    {}).fail(function (xhr, status, exception)
    {
        alert("ViewRoomTypeUI" + xhr.status + exception);
    })
    $j.getScript("/netmd/js/netmd/ui/Room/ViewRoomPTB.js").done(function (script, textStatus)
    {}).fail(function (xhr, status, exception)
    {
        alert("ViewRoomTypePTB" + xhr.status + exception);
    })
    $j.getScript("/netmd/js/netmd/ui/Room/NewRoomUI.js").done(function (script, textStatus)
    {}).fail(function (xhr, status, exception)
    {
        alert("NewRoomTypeUI" + xhr.status + exception);
    })

    /* Department UI Pages */
    $j.getScript("/netmd/js/netmd/ui/Department/DepartmentUIStartup.js").done(function (script, textStatus)
    {}).fail(function (xhr, status, exception)
    {
        alert("DepartmentUIStartup" + xhr + exception);
    })

    $j.getScript("/netmd/js/netmd/ui/Department/NewDepartmentUI.js").done(function (script, textStatus)
    {}).fail(function (xhr, status, exception)
    {
        alert("NewDepartmentUI" + xhr + exception);
    })
    $j.getScript("/netmd/js/netmd/ui/Department/ViewDepartmentUI.js").done(function (script, textStatus)
    {}).fail(function (xhr, status, exception)
    {
        alert("ViewDepartmentUI" + xhr + exception);
    })
    $j.getScript("/netmd/js/netmd/ui/Department/ViewDepartmentPTB.js").done(function (script, textStatus)
    {}).fail(function (xhr, status, exception)
    {
        alert("ViewDepartmentPTB" + xhr + exception);
    }) /* Tax UI*/
    $j.getScript("/netmd/js/netmd/ui/Tax/TaxUIStartup.js").done(function (script, textStatus)
    {}).fail(function (xhr, status, exception)
    {
        alert("TaxUIStartup" + xhr + exception);
    })

    $j.getScript("/netmd/js/netmd/ui/Tax/NewTaxUI.js").done(function (script, textStatus)
    {}).fail(function (xhr, status, exception)
    {
        alert("NewTaxUI" + xhr + exception);
    })
    $j.getScript("/netmd/js/netmd/ui/Tax/ViewTaxUI.js").done(function (script, textStatus)
    {}).fail(function (xhr, status, exception)
    {
        alert("ViewTaxUI" + xhr + exception);
    })
    $j.getScript("/netmd/js/netmd/ui/Tax/ViewTaxPTB.js").done(function (script, textStatus)
    {}).fail(function (xhr, status, exception)
    {
        alert("ViewTaxPTB" + xhr + exception);
    }) /* Block Validator */
    $j.getScript("/netmd/js/netmd/validation/BlockValidator.js").done(function (script, textStatus)
    {}).fail(function (xhr, status, exception)
    {
        alert("NewRoomTypeUI" + xhr.status + exception);
    })

    /* Department Validator */
    $j.getScript("/netmd/js/netmd/validation/DepartmentValidator.js").done(function (script, textStatus)
    {}).fail(function (xhr, status, exception)
    {
        alert("DepartmentValidator" + xhr.status + exception);
    }) /* BedType Validator */
    $j.getScript("/netmd/js/netmd/validation/BedTypeValidator.js").done(function (script, textStatus)
    {}).fail(function (xhr, status, exception)
    {
        alert("BedTypeValidator" + xhr.status + exception);
    }) /* RoomType Validator */
    $j.getScript("/netmd/js/netmd/validation/RoomTypeValidator.js").done(function (script, textStatus)
    {}).fail(function (xhr, status, exception)
    {
        alert("BedTypeValidator" + xhr.status + exception);
    }) /* Tax Validator */
    $j.getScript("/netmd/js/netmd/validation/TaxValidator.js").done(function (script, textStatus)
    {}).fail(function (xhr, status, exception)
    {
        alert("TaxValidator" + xhr.status + exception);
    })
	/* Room Validator*/
	$j.getScript("/netmd/js/netmd/validation/RoomValidator.js").done(function (script, textStatus)
    {}).fail(function (xhr, status, exception)
    {
        alert("RoomValidator" + xhr.status + exception);
    })	
	/* Support Validator*/
		$j.getScript("/netmd/js/netmd/validation/SupportValidator.js").done(function (script, textStatus)
    {}).fail(function (xhr, status, exception)
    {
        alert("SupportValidator" + xhr.status + exception);
    })	
	/* Bed Validator*/
		$j.getScript("/netmd/js/netmd/validation/BedValidator.js").done(function (script, textStatus)
    {}).fail(function (xhr, status, exception)
    {
        alert("BedValidator" + xhr.status + exception);
    })	
	/* setting Validator*/
	 $j.getScript("/netmd/js/netmd/validation/SettingValidator.js").done(function (script, textStatus)
    {}).fail(function (xhr, status, exception)
    {
        alert("SettingValidator" + xhr.status + exception);
    })
	/* case Validator*/
	 $j.getScript("/netmd/js/netmd/validation/CaseValidator.js").done(function (script, textStatus)
    {}).fail(function (xhr, status, exception)
    {
        alert("CaseValidator" + xhr.status + exception);
    })
		/* Patient Validator*/
		$j.getScript("/netmd/js/netmd/validation/PatientValidator.js").done(function (script, textStatus)
    {}).fail(function (xhr, status, exception)
    {
        alert("PatientValidator" + xhr.status + exception);
    })	
	/*--- Block Service JS ---*/
	 $j.getScript("/netmd/js/netmd/impl/PatientServiceImpl.js").done(function (script, textStatus)
    {}).fail(function (xhr, status, exception)
    {
        alert("PatientServiceImpl" + xhr.status + exception);
    })
    $j.getScript("/netmd/js/netmd/impl/BlockServiceImpl.js").done(function (script, textStatus)
    {}).fail(function (xhr, status, exception)
    {
        alert("BlockServiceImpl" + xhr.status + exception);
    })
    $j.getScript("/netmd/js/netmd/impl/BedTypeServiceImpl.js").done(function (script, textStatus)
    {}).fail(function (xhr, status, exception)
    {
        alert("BedTypeServiceImpl" + xhr.status + exception);
    })
    $j.getScript("/netmd/js/netmd/impl/ItemServiceImpl.js").done(function (script, textStatus)
    {}).fail(function (xhr, status, exception)
    {
        alert("ItemServiceImpl" + xhr.status + exception);
    })
    $j.getScript("/netmd/js/netmd/impl/RoomTypeServiceImpl.js").done(function (script, textStatus)
    {}).fail(function (xhr, status, exception)
    {
        alert("RoomTypeServiceImpl" + xhr.status + exception);
    })
    $j.getScript("/netmd/js/netmd/impl/RoomServiceImpl.js").done(function (script, textStatus)
    {}).fail(function (xhr, status, exception)
    {
        alert("RoomTypeServiceImpl" + xhr.status + exception);
    })
    $j.getScript("/netmd/js/netmd/impl/DepartmentServiceImpl.js").done(function (script, textStatus)
    {}).fail(function (xhr, status, exception)
    {
        alert("DepartmentServiceImpl" + xhr.status + exception);
    })
    $j.getScript("/netmd/js/netmd/impl/TaxServiceImpl.js").done(function (script, textStatus)
    {}).fail(function (xhr, status, exception)
    {
        alert("TaxServiceImpl" + xhr.status + exception);
    })
	 $j.getScript("/netmd/js/netmd/impl/SupportServiceImpl.js").done(function (script, textStatus)
    {}).fail(function (xhr, status, exception)
    {
        alert("SupportServiceImpl" + xhr.status + exception);
    })
	 $j.getScript("/netmd/js/netmd/impl/BedServiceImpl.js").done(function (script, textStatus)
    {}).fail(function (xhr, status, exception)
    {
        alert("BedServiceImpl" + xhr.status + exception);
    })
	 $j.getScript("/netmd/js/netmd/impl/BillServiceImpl.js").done(function (script, textStatus)
    {}).fail(function (xhr, status, exception)
    {
        alert("BillServiceImpl" + xhr.status + exception);
    })
	 $j.getScript("/netmd/js/netmd/impl/DiscountServiceImpl.js").done(function (script, textStatus)
    {}).fail(function (xhr, status, exception)
    {
        alert("DiscountServiceImpl" + xhr.status + exception);
    })
	 $j.getScript("/netmd/js/netmd/impl/AdvanceServiceImpl.js").done(function (script, textStatus)
    {}).fail(function (xhr, status, exception)
    {
        alert("AdvanceServiceImpl" + xhr.status + exception);
    })
	$j.getScript("/netmd/js/netmd/impl/HeadServiceImpl.js").done(function (script, textStatus)
    {}).fail(function (xhr, status, exception)
    {
        alert("HeadServiceImpl" + xhr.status + exception);
    })
	$j.getScript("/netmd/js/netmd/impl/ExpenseServiceImpl.js").done(function (script, textStatus)
    {}).fail(function (xhr, status, exception)
    {
        alert("ExpenseServiceImpl" + xhr.status + exception);
    })
	$j.getScript("/netmd/js/netmd/impl/SettingServiceImpl.js").done(function (script, textStatus)
    {}).fail(function (xhr, status, exception)
    {
        alert("SettingServiceImpl" + xhr.status + exception);
    })
    $j.getScript("/netmd/js/netmd/impl/GyneServiceImpl.js").done(function (script, textStatus)
    {}).fail(function (xhr, status, exception)
    {
        alert("GyneServiceImpl" + xhr.status + exception);
    })
	    $j.getScript("/netmd/js/netmd/impl/CaseServiceImpl.js").done(function (script, textStatus)
    {}).fail(function (xhr, status, exception)
    {
        alert("GyneServiceImpl" + xhr.status + exception);
    })
	    $j.getScript("/netmd/js/netmd/impl/MedicalRecordServiceImpl.js").done(function (script, textStatus)
    {}).fail(function (xhr, status, exception)
    {
        alert("GyneServiceImpl" + xhr.status + exception);
    })
	$j.getScript("/netmd/js/netmd/impl/QuestionnaireServiceImpl.js").done(function (script, textStatus)
    {}).fail(function (xhr, status, exception)
    {
        alert("QuestionnaireServiceImpl" + xhr + exception);
    })
    /* Item UI Pages */

    $j.getScript("/netmd/js/netmd/ui/Item/ItemUIStartup.js").done(function (script, textStatus)
    {}).fail(function (xhr, status, exception)
    {
        alert("ItemUIStartup" + xhr + exception);
    })
    $j.getScript("/netmd/js/netmd/ui/Item/NewItemUI.js").done(function (script, textStatus)
    {}).fail(function (xhr, status, exception)
    {
        alert("NewItemUI" + xhr + exception);
    })
    $j.getScript("/netmd/js/netmd/ui/Item/ViewItemUI.js").done(function (script, textStatus)
    {}).fail(function (xhr, status, exception)
    {
        alert("ViewItemUI" + xhr + exception);
    })
    $j.getScript("/netmd/js/netmd/ui/Item/ViewItemPTB.js").done(function (script, textStatus)
    {}).fail(function (xhr, status, exception)
    {
        alert("ViewItemPTB" + xhr + exception);
    })
	 $j.getScript("/netmd/js/netmd/validation/ItemValidator.js").done(function (script, textStatus)
    {}).fail(function (xhr, status, exception)
    {
        alert("ItemValidator" + xhr.status + exception);
    })
        /* Support UI Pages */

    $j.getScript("/netmd/js/netmd/ui/Support/SupportUIStartup.js").done(function (script, textStatus)
    {}).fail(function (xhr, status, exception)
    {
        alert("ItemUIStartup" + xhr + exception);
    })
    $j.getScript("/netmd/js/netmd/ui/Support/NewSupportUI.js").done(function (script, textStatus)
    {}).fail(function (xhr, status, exception)
    {
        alert("NewSupportUI" + xhr + exception);
    })
    $j.getScript("/netmd/js/netmd/ui/Support/ViewSupportUI.js").done(function (script, textStatus)
    {}).fail(function (xhr, status, exception)
    {
        alert("ViewSupportUI" + xhr + exception);
    })
    $j.getScript("/netmd/js/netmd/ui/Support/ViewSupportPTB.js").done(function (script, textStatus)
    {}).fail(function (xhr, status, exception)
    {
        alert("ViewSupportPTB" + xhr + exception);
    })
	/* Support Validator */
    $j.getScript("/netmd/js/netmd/validation/SupportValidator.js").done(function (script, textStatus)
    {}).fail(function (xhr, status, exception)
    {
        alert("SupportValidator" + xhr.status + exception);
    })
	     /* Bed UI Pages */
    $j.getScript("/netmd/js/netmd/ui/Bed/BedUIStartup.js").done(function (script, textStatus)
    {}).fail(function (xhr, status, exception)
    {
        alert("BedUIStartup" + xhr + exception);
    })
    $j.getScript("/netmd/js/netmd/ui/Bed/NewBedUI.js").done(function (script, textStatus)
    {}).fail(function (xhr, status, exception)
    {
        alert("NewBedUI" + xhr + exception);
    })
    $j.getScript("/netmd/js/netmd/ui/Bed/ViewBedUI.js").done(function (script, textStatus)
    {}).fail(function (xhr, status, exception)
    {
        alert("ViewBedUI" + xhr + exception);
    })
    $j.getScript("/netmd/js/netmd/ui/Bed/ViewBedPTB.js").done(function (script, textStatus)
    {}).fail(function (xhr, status, exception)
    {
        alert("ViewBedPTB" + xhr + exception);
    })
	  /* Bill UI Pages */
    $j.getScript("/netmd/js/netmd/ui/Bill/BillUIStartup.js").done(function (script, textStatus)
    {}).fail(function (xhr, status, exception)
    {
        alert("BillUIStartup" + xhr + exception);
    })
    $j.getScript("/netmd/js/netmd/ui/Bill/NewBillUI.js").done(function (script, textStatus)
    {}).fail(function (xhr, status, exception)
    {
        alert("NewBillUI" + xhr + exception);
    })
    $j.getScript("/netmd/js/netmd/ui/Bill/ViewBillUI.js").done(function (script, textStatus)
    {}).fail(function (xhr, status, exception)
    {
        alert("ViewBillUI" + xhr + exception);
    })
	 $j.getScript("/netmd/js/netmd/ui/Bill/ViewBillHeaderProcessor.js").done(function (script, textStatus)
    {}).fail(function (xhr, status, exception)
    {
        alert("ViewBillHeaderProcessor" + xhr + exception);
    })
	$j.getScript("/netmd/js/netmd/ui/Bill/ViewItemServiceProcessor.js").done(function (script, textStatus)
    {}).fail(function (xhr, status, exception)
    {
        alert("ViewItemServiceProcessor" + xhr + exception);
    })
	$j.getScript("/netmd/js/netmd/ui/Bill/ViewDiscountProcessor.js").done(function (script, textStatus)
    {}).fail(function (xhr, status, exception)
    {
        alert("ViewDiscountProcessor" + xhr + exception);
    })
	$j.getScript("/netmd/js/netmd/ui/Bill/ViewBillPaymentProcessor.js").done(function (script, textStatus)
    {}).fail(function (xhr, status, exception)
    {
        alert("ViewBillPaymentProcessor" + xhr + exception);
    })
	$j.getScript("/netmd/js/netmd/ui/Bill/NewBillDiscountProcessor.js").done(function (script, textStatus)
    {}).fail(function (xhr, status, exception)
    {
        alert("NewBillDiscountProcessor" + xhr.status + exception);
    })
	$j.getScript("/netmd/js/netmd/ui/Bill/NewBillPaymentProcessor.js").done(function (script, textStatus)
    {}).fail(function (xhr, status, exception)
    {
        alert("NewBillPaymentProcessor" + xhr.status + exception);
    })
	$j.getScript("/netmd/js/netmd/ui/Bill/ChangeBillStatusUI.js").done(function (script, textStatus)
    {}).fail(function (xhr, status, exception)
    {
        alert("ChangeBillStatusUI" + xhr.status + exception);
    })
	$j.getScript("/netmd/js/netmd/ui/Bill/DiscountVoucherProcessor.js").done(function (script, textStatus)
    {}).fail(function (xhr, status, exception)
    {
        alert("DiscountVoucherProcessor" + xhr + exception);
    })
    $j.getScript("/netmd/js/netmd/ui/Bill/ViewBillPTB.js").done(function (script, textStatus)
    {}).fail(function (xhr, status, exception)
    {
        alert("ViewBillPTB" + xhr + exception);
    })
	/* Bill Validator */
    $j.getScript("/netmd/js/netmd/validation/BillValidator.js").done(function (script, textStatus)
    {}).fail(function (xhr, status, exception)
    {
        alert("BillValidator" + xhr.status + exception);
    })
	

	
	  /* Discount UI Pages */
    $j.getScript("/netmd/js/netmd/ui/Discount/DiscountUIStartup.js").done(function (script, textStatus)
    {}).fail(function (xhr, status, exception)
    {
        alert("DiscountUIStartup" + xhr + exception);
    })
    $j.getScript("/netmd/js/netmd/ui/Discount/NewDiscountUI.js").done(function (script, textStatus)
    {}).fail(function (xhr, status, exception)
    {
        alert("NewDiscountUI" + xhr + exception);
    })
    $j.getScript("/netmd/js/netmd/ui/Discount/ViewDiscountUI.js").done(function (script, textStatus)
    {}).fail(function (xhr, status, exception)
    {
        alert("ViewDiscountUI" + xhr + exception);
    })
    $j.getScript("/netmd/js/netmd/ui/Discount/ViewDiscountPTB.js").done(function (script, textStatus)
    {}).fail(function (xhr, status, exception)
    {
        alert("ViewDiscountPTB" + xhr + exception);
    })
	/* Discount Validator */
    $j.getScript("/netmd/js/netmd/validation/DiscountValidator.js").done(function (script, textStatus)
    {}).fail(function (xhr, status, exception)
    {
        alert("DiscountValidator" + xhr.status + exception);
    })
	
	 /* Advance UI Pages */
    $j.getScript("/netmd/js/netmd/ui/Advance/AdvanceUIStartup.js").done(function (script, textStatus)
    {}).fail(function (xhr, status, exception)
    {
        alert("AdvanceUIStartup" + xhr + exception);
    })
    $j.getScript("/netmd/js/netmd/ui/Advance/NewAdvanceUI.js").done(function (script, textStatus)
    {}).fail(function (xhr, status, exception)
    {
        alert("NewAdvanceUI" + xhr + exception);
    })
    $j.getScript("/netmd/js/netmd/ui/Advance/ViewAdvanceUI.js").done(function (script, textStatus)
    {}).fail(function (xhr, status, exception)
    {
        alert("ViewAdvanceUI" + xhr + exception);
    })
    $j.getScript("/netmd/js/netmd/ui/Advance/ViewAdvancePTB.js").done(function (script, textStatus)
    {}).fail(function (xhr, status, exception)
    {
        alert("ViewAdvancePTB" + xhr + exception);
    })
	
	/* Head UI Pages */	
	$j.getScript("/netmd/js/netmd/ui/Head/HeadUIStartup.js").done(function (script, textStatus)
    {}).fail(function (xhr, status, exception)
    {
        alert("HeadUIStartup" + xhr + exception);
    })
	$j.getScript("/netmd/js/netmd/ui/Head/NewHeadUI.js").done(function (script, textStatus)
    {}).fail(function (xhr, status, exception)
    {
        alert("NewHeadUI" + xhr + exception);
    })
    $j.getScript("/netmd/js/netmd/ui/Head/ViewHeadUI.js").done(function (script, textStatus)
    {}).fail(function (xhr, status, exception)
    {
        alert("ViewHeadUI" + xhr + exception);
    })
    $j.getScript("/netmd/js/netmd/ui/Head/ViewHeadPTB.js").done(function (script, textStatus)
    {}).fail(function (xhr, status, exception)
    {
        alert("ViewHeadPTB" + xhr + exception);
    })
	
	/* Expense UI Pages*/
	$j.getScript("/netmd/js/netmd/ui/Expense/ExpenseUIStartup.js").done(function (script, textStatus)
    {}).fail(function (xhr, status, exception)
    {
        alert("ExpenseUIStartup" + xhr + exception);
    })
	$j.getScript("/netmd/js/netmd/ui/Expense/NewExpenseUI.js").done(function (script, textStatus)
    {}).fail(function (xhr, status, exception)
    {
        alert("NewExpenseUI" + xhr + exception);
    })
    $j.getScript("/netmd/js/netmd/ui/Expense/ViewExpenseUI.js").done(function (script, textStatus)
    {}).fail(function (xhr, status, exception)
    {
        alert("ViewExpenseUI" + xhr + exception);
    })
    $j.getScript("/netmd/js/netmd/ui/Expense/ViewExpensePTB.js").done(function (script, textStatus)
    {}).fail(function (xhr, status, exception)
    {
        alert("ViewExpensePTB" + xhr + exception);
    })
	 
	 
	/* Advance Validator */
    $j.getScript("/netmd/js/netmd/validation/AdvanceValidator.js").done(function (script, textStatus)
    {}).fail(function (xhr, status, exception)
    {
        alert("AdvanceValidator" + xhr.status + exception);
    })
	
	/* Head Validator */
    $j.getScript("/netmd/js/netmd/validation/HeadValidator.js").done(function (script, textStatus)
    {}).fail(function (xhr, status, exception)
    {
        alert("HeadValidator" + xhr.status + exception);
    })
	
	 /*Expense Validator */
    $j.getScript("/netmd/js/netmd/validation/ExpenseValidator.js").done(function (script, textStatus)
    {}).fail(function (xhr, status, exception)
    {
        alert("ExpenseValidator" + xhr.status + exception);
    })
			
	/* //Report
	
	$j.getScript("/netmd/js/netmd/ui/report/ReportUIStartup.js").done(function(script,textStatus){
        }).fail(function (xhr, status, exception)
        {
        alert("ReportUIStartup" + xhr.status + exception);
        })
	$j.getScript("/netmd/js/netmd/impl/ReportServiceImpl.js").done(function(script,textStatus){
        }).fail(function (xhr, status, exception)
        {
        alert("ReportServiceImpl" + xhr.status + exception);
        })
		$j.getScript("/netmd/js/netmd/dto/ReportDTO.js").done(function(script,textStatus){
        }).fail(function (xhr, status, exception)
        {
        alert("ReportDTO" + xhr.status + exception);
        })
		$j.getScript("/netmd/js/netmd/validation/ReportValidator.js").done(function(script,textStatus){
        }).fail(function (xhr, status, exception)
        {
        alert("ReportValidator" + xhr.status + exception);
        }) */
	
	
	/* case List UI pages */
	
	$j.getScript("/netmd/js/netmd/ui/caseList/CaseListUIStartup.js").done(function (script, textStatus)
    {}).fail(function (xhr, status, exception)
    {
        alert("CaseListUIStartup" + xhr.status + exception);
    })
	
	
	 $j.getScript("/netmd/js/netmd/ui/caseList/ViewCaseListUI.js").done(function (script, textStatus)
    {}).fail(function (xhr, status, exception)
    {
        alert("ViewCaseListUI" + xhr + exception);
    })
	
	$j.getScript("/netmd/js/netmd/ui/caseList/ViewCaseListPTB.js").done(function (script, textStatus)
    {}).fail(function (xhr, status, exception)
    {
        alert("ViewCaseListPTB" + xhr + exception);
    }) 
	
	

		/*Case UI Pages*/
	
    $j.getScript("/netmd/js/netmd/ui/Case/CaseUIStartup.js").done(function (script, textStatus)
    {}).fail(function (xhr, status, exception)
    {
        alert("CaseUIStartup" + xhr + exception);
    })
    $j.getScript("/netmd/js/netmd/ui/Case/NewCaseUI.js").done(function (script, textStatus)
    {}).fail(function (xhr, status, exception)
    {
        alert("NewCaseUI" + xhr + exception);
    })
    $j.getScript("/netmd/js/netmd/ui/Case/ViewCaseUI.js").done(function (script, textStatus)
    {}).fail(function (xhr, status, exception)
    {
        alert("ViewCaseUI" + xhr + exception);
    })
    $j.getScript("/netmd/js/netmd/ui/Case/ViewCasePTB.js").done(function (script, textStatus)
    {}).fail(function (xhr, status, exception)
    {
        alert("ViewCasePTB" + xhr + exception);
    })
	
	$j.getScript("/netmd/js/netmd/ui/Case/GynQuestionnaire.js").done(function (script, textStatus)
    {}).fail(function (xhr, status, exception)
    {
        alert("GynQuestionnaire" + xhr + exception);
    })
	$j.getScript("/netmd/js/netmd/ui/Case/GyneModalProcessor.js").done(function (script, textStatus)
    {}).fail(function (xhr, status, exception)
    {
        alert("GyneModalProcessor" + xhr + exception);
    })
	/* New Questionnaire  UI Pages*/
	$j.getScript("/netmd/js/netmd/ui/Questionnaire/QuestionnaireUIStartUp.js").done(function (script, textStatus)
    {}).fail(function (xhr, status, exception)
    {
        alert("QuestionnaireUIStartUp" + xhr + exception);
    })
	$j.getScript("/netmd/js/netmd/ui/Questionnaire/NewQuestionnaireUI.js").done(function (script, textStatus)
    {}).fail(function (xhr, status, exception)
    {
        alert("NewQuestionnaireUI" + xhr + exception);
    })
 	$j.getScript("/netmd/js/netmd/ui/Questionnaire/ViewQuestionnaireUI.js").done(function (script, textStatus)
    {}).fail(function (xhr, status, exception)
    {
        alert("ViewQuestionnaireUI" + xhr + exception);
    }) 
	$j.getScript("/netmd/js/netmd/ui/Questionnaire/ViewQuestionnairePTB.js").done(function (script, textStatus)
    {}).fail(function (xhr, status, exception)
    {
        alert("ViewQuestionnairePTB" + xhr + exception);
    })
	$j.getScript("/netmd/js/netmd/ui/Questionnaire/newQuestionnaireProcessor.js").done(function (script, textStatus)
    {}).fail(function (xhr, status, exception)
    {
        alert("newQuestionnaireProcessor" + xhr + exception);
    })
	
	$j.getScript("/netmd/js/netmd/validation/QuestionnaireValidator.js").done(function (script, textStatus)
    {}).fail(function (xhr, status, exception)
    {
        alert("QuestionnaireValidator" + xhr + exception);
    })
	
	$j.getScript("/netmd/js/netmd/dto/NetmdQuestionAnswerDTO.js").done(function (script, textStatus)
    {}).fail(function (xhr, status, exception)
    {
        alert("NetmdQuestionAnswerDTO" + xhr + exception);
    })
	/*MedicalRecord UI Pages*/
	  $j.getScript("/netmd/js/netmd/ui/MedicalRecord/MedicalRecordUIStartup.js").done(function (script, textStatus)
    {}).fail(function (xhr, status, exception)
    {
        alert("MedicalRecordUIStartup" + xhr + exception);
    })
    $j.getScript("/netmd/js/netmd/ui/MedicalRecord/NewMedicalRecordUI.js").done(function (script, textStatus)
    {}).fail(function (xhr, status, exception)
    {
        alert("NewMedicalRecordUI" + xhr + exception);
    })
    $j.getScript("/netmd/js/netmd/ui/MedicalRecord/ViewMedicalRecordUI.js").done(function (script, textStatus)
    {}).fail(function (xhr, status, exception)
    {
        alert("ViewMedicalRecordUI" + xhr + exception);
    })
    $j.getScript("/netmd/js/netmd/ui/MedicalRecord/ViewMedicalRecordPTB.js").done(function (script, textStatus)
    {}).fail(function (xhr, status, exception)
    {
        alert("ViewMedicalRecordPTB" + xhr + exception);
    })
	$j.getScript("/netmd/js/netmd/validation/MedicalRecordValidator.js").done(function (script, textStatus)
    {}).fail(function (xhr, status, exception)
    {
        alert("MedicalRecordValidator" + xhr.status + exception);
    })

	
}

