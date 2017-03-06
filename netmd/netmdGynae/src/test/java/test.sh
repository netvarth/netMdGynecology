curl -H "Accept: application/json" -H "Content-type: application/json; charset=UTF-8" -X POST --data @/var/lib/jenkins/jobs/workspace/netmd1.0/systemTest/login.txt http://192.168.1.99:8002/netmd/ws/ui/auth/login -c cookies.txt >c.txt | if ! grep -q ""success":true,"error":null" ;then echo "exit1";fi`



curl  http://192.168.1.99:8002/netmd/ws/ui/auth/logout -b cookies.txt >c.txt | if ! grep -q ""success":true,"error":null" ;then echo "exit1";fi`

curl -H "Accept: application/json" -H "Content-type: application/json; charset=UTF-8" -X POST --data @/var/lib/jenkins/jobs/workspace/netmd1.0/systemTest/preLogin.txt http://192.168.1.99:8002/netmd/ws/ui/sync/activateNetMd -c cookies.txt >c.txt | if ! grep -q ""success":true,"error":null" ;then echo "exit1";fi`

curl -H "Accept: application/json" -H "Content-type: application/json; charset=UTF-8" -X POST --data @/var/lib/jenkins/jobs/workspace/netmd1.0/systemTest/appcreate.txt http://192.168.1.99:8002/netmd/ws/ui/appointment/create -b cookies.txt >c.txt | if ! grep -q ""success":true,"error":null" ;then echo "exit1";fi`
curl -H "Accept: application/json" -H "Content-type: application/json; charset=UTF-8" -X POST --data @/var/lib/jenkins/jobs/workspace/netmd1.0/systemTest/appupdate.txt http://192.168.1.99:8002/netmd/ws/ui/appointment/update -b cookies.txt >c.txt | if ! grep -q ""success":true,"error":null" ;then echo "exit1";fi`

curl -H "Accept: application/json" -H "Content-type: application/json; charset=UTF-8" -X POST --data @/var/lib/jenkins/jobs/workspace/netmd1.0/systemTest/netmdquestionaire.txt http://192.168.1.99:8002/netmd/ws/ui/questionnaire/NetmdQuestionnaire -b cookies.txt >c.txt | if ! grep -q ""success":true,"error":null" ;then echo "exit1";fi`
curl -H "Accept: application/json" -H "Content-type: application/json; charset=UTF-8" -X POST --data @/var/lib/jenkins/jobs/workspace/netmd1.0/systemTest/questnlist.txt http://192.168.1.99:8002/netmd/ws/ui/questionnaire/list -b cookies.txt >c.txt | if ! grep -q ""success":true,"error":null" ;then echo "exit1";fi`
curl -H "Accept: application/json" -H "Content-type: application/json; charset=UTF-8" -X POST --data @/var/lib/jenkins/jobs/workspace/netmd1.0/systemTest/updateQuestionnaire.txt http://192.168.1.99:8002/netmd/ws/ui/questionnaire/updateQuestionnaire -b cookies.txt >c.txt | if ! grep -q ""success":true,"error":null" ;then echo "exit1";fi`

curl -H "Accept: application/json" -H "Content-type: application/json; charset=UTF-8" -X POST --data @/var/lib/jenkins/jobs/workspace/netmd1.0/systemTest/doctorlist.txt http://192.168.1.99:8002/netmd/ws/ui/doctor/list -b cookies.txt >c.txt | if ! grep -q ""success":true,"error":null" ;then echo "exit1";fi`
curl -H "Accept: application/json" -H "Content-type: application/json; charset=UTF-8" -X POST --data @/var/lib/jenkins/jobs/workspace/netmd1.0/systemTest/doctorinfo.txt http://192.168.1.99:8002/netmd/ws/ui/doctor/createPersonalInfo -b cookies.txt >c.txt | if ! grep -q ""success":true,"error":null" ;then echo "exit1";fi`
curl -H "Accept: application/json" -H "Content-type: application/json; charset=UTF-8" -X POST --data @/var/lib/jenkins/jobs/workspace/netmd1.0/systemTest/updateinfo.txt http://192.168.1.99:8002/netmd/ws/ui/doctor/updatePersonalInfo -b cookies.txt >c.txt | if ! grep -q ""success":true,"error":null" ;then echo "exit1";fi`
curl -H "Accept: application/json" -H "Content-type: application/json; charset=UTF-8" -X POST --data @/var/lib/jenkins/jobs/workspace/netmd1.0/systemTest/doctorqualification.txt http://192.168.1.99:8002/netmd/ws/ui/doctor/doctorQualification -b cookies.txt >c.txt | if ! grep -q ""success":true,"error":null" ;then echo "exit1";fi`
curl -H "Accept: application/json" -H "Content-type: application/json; charset=UTF-8" -X POST --data @/var/lib/jenkins/jobs/workspace/netmd1.0/systemTest/doctorachievement.txt http://192.168.1.99:8002/netmd/ws/ui/doctor/doctorAchievement -b cookies.txt >c.txt | if ! grep -q ""success":true,"error":null" ;then echo "exit1";fi`
curl -H "Accept: application/json" -H "Content-type: application/json; charset=UTF-8" -X POST --data @/var/lib/jenkins/jobs/workspace/netmd1.0/systemTest/doctorexperience.txt http://192.168.1.99:8002/netmd/ws/ui/doctor/doctorExperience -b cookies.txt >c.txt | if ! grep -q ""success":true,"error":null" ;then echo "exit1";fi`
curl -H "Accept: application/json" -H "Content-type: application/json; charset=UTF-8" -X POST --data @/var/lib/jenkins/jobs/workspace/netmd1.0/systemTest/doctorexpertise.txt http://192.168.1.99:8002/netmd/ws/ui/doctor/doctorExpertise -b cookies.txt >c.txt | if ! grep -q ""success":true,"error":null" ;then echo "exit1";fi`
curl -H "Accept: application/json" -H "Content-type: application/json; charset=UTF-8" -X POST --data @/var/lib/jenkins/jobs/workspace/netmd1.0/systemTest/doctormembership.txt http://192.168.1.99:8002/netmd/ws/ui/doctor/doctorMemberShip -b cookies.txt >c.txt | if ! grep -q ""success":true,"error":null" ;then echo "exit1";fi`

curl -H "Accept: application/json" -H "Content-type: application/json; charset=UTF-8" -X POST --data @/var/lib/jenkins/jobs/workspace/netmd1.0/systemTest/getmessage.txt http://192.168.1.99:8002/netmd/ws/ui/message/getMessage -b cookies.txt >c.txt | if ! grep -q ""success":true,"error":null" ;then echo "exit1";fi`

curl -H "Accept: application/json" -H "Content-type: application/json; charset=UTF-8" -X POST --data @/var/lib/jenkins/jobs/workspace/netmd1.0/systemTest/createpatient.txt http://192.168.1.99:8002/netmd/ws/ui/patient/create -b cookies.txt >c.txt | if ! grep -q ""success":true,"error":null" ;then echo "exit1";fi`
curl -H "Accept: application/json" -H "Content-type: application/json; charset=UTF-8" -X POST --data @/var/lib/jenkins/jobs/workspace/netmd1.0/systemTest/patientupdate.txt http://192.168.1.99:8002/netmd/ws/ui/patient/update -b cookies.txt >c.txt | if ! grep -q ""success":true,"error":null" ;then echo "exit1";fi`
curl -H "Accept: application/json" -H "Content-type: application/json; charset=UTF-8" -X POST --data @/var/lib/jenkins/jobs/workspace/netmd1.0/systemTest/patientlist.txt http://192.168.1.99:8002/netmd/ws/ui/patient/list-b cookies.txt >c.txt | if ! grep -q ""success":true,"error":null" ;then echo "exit1";fi`
curl -H "Accept: application/json" -H "Content-type: application/json; charset=UTF-8" -X POST --data @/var/lib/jenkins/jobs/workspace/netmd1.0/systemTest/listofcase.txt http://192.168.1.99:8002/netmd/ws/ui/patient/listOfCase -b cookies.txt >c.txt | if ! grep -q ""success":true,"error":null" ;then echo "exit1";fi`
#curl -H "Accept: application/json" -H "Content-type: application/json; charset=UTF-8" -X POST --data @/var/lib/jenkins/jobs/workspace/netmd1.0/systemTest/listofmedicalrecord.txt http://192.168.1.99:8002/netmd/ws/ui/patient/listOfMedicalRecord -b cookies.txt >c.txt | if ! grep -q ""success":true,"error":null" ;then echo "exit1";fi`
curl -H "Accept: application/json" -H "Content-type: application/json; charset=UTF-8" -X POST --data @/var/lib/jenkins/jobs/workspace/netmd1.0/systemTest/autosave.txt http://192.168.1.99:8002/netmd/ws/ui/patient/AutoSaveCase -b cookies.txt >c.txt | if ! grep -q ""success":true,"error":null" ;then echo "exit1";fi`
curl -H "Accept: application/json" -H "Content-type: application/json; charset=UTF-8" -X POST --data @/var/lib/jenkins/jobs/workspace/netmd1.0/systemTest/createcase.txt http://192.168.1.99:8002/netmd/ws/ui/patient/createCase -b cookies.txt >c.txt | if ! grep -q ""success":true,"error":null" ;then echo "exit1";fi`
curl -H "Accept: application/json" -H "Content-type: application/json; charset=UTF-8" -X POST --data @/var/lib/jenkins/jobs/workspace/netmd1.0/systemTest/createmedrecord.txt http://192.168.1.99:8002/netmd/ws/ui/patient/createMedicalRecord-b cookies.txt >c.txt | if ! grep -q ""success":true,"error":null" ;then echo "exit1";fi`
curl -H "Accept: application/json" -H "Content-type: application/json; charset=UTF-8" -X POST --data @/var/lib/jenkins/jobs/workspace/netmd1.0/systemTest/updatcase.txt http://192.168.1.99:8002/netmd/ws/ui/patient/updateCase -b cookies.txt >c.txt | if ! grep -q ""success":true,"error":null" ;then echo "exit1";fi`
#curl -H "Accept: application/json" -H "Content-type: application/json; charset=UTF-8" -X POST --data @/var/lib/jenkins/jobs/workspace/netmd1.0/systemTest/updatemedrecord.txt http://192.168.1.99:8002/netmd/ws/ui/patient/updateMedicalRecord-b cookies.txt >c.txt | if ! grep -q ""success":true,"error":null" ;then echo "exit1";fi`

#curl -H "Accept: application/json" -H "Content-type: application/json; charset=UTF-8" -X POST --data @/var/lib/jenkins/jobs/workspace/netmd1.0/systemTest/resultlist.txt http://192.168.1.99:8002/netmd/ws/ui/result/list -b cookies.txt >c.txt | if ! grep -q ""success":true,"error":null" ;then echo "exit1";fi`

curl -H "Accept: application/json" -H "Content-type: application/json; charset=UTF-8" -X POST --data @/var/lib/jenkins/jobs/workspace/netmd1.0/systemTest/createschedule.txt http://192.168.1.99:8002/netmd/ws/ui/schedule/create-b cookies.txt >c.txt | if ! grep -q ""success":true,"error":null" ;then echo "exit1";fi`
curl -H "Accept: application/json" -H "Content-type: application/json; charset=UTF-8" -X POST --data @/var/lib/jenkins/jobs/workspace/netmd1.0/systemTest/updateschedule.txt http://192.168.1.99:8002/netmd/ws/ui/schedule/update -b cookies.txt >c.txt | if ! grep -q ""success":true,"error":null" ;then echo "exit1";fi`

#curl -H "Accept: application/json" -H "Content-type: application/json; charset=UTF-8" -X POST --data @/var/lib/jenkins/jobs/workspace/netmd1.0/systemTest/activate.txt http://192.168.1.99:8002/netmd/ws/ui/sync/activateNetMd -b cookies.txt >c.txt | if ! grep -q ""success":true,"error":null" ;then echo "exit1";fi`

#curl -H "Accept: application/json" -H "Content-type: application/json; charset=UTF-8" -X POST --data @/var/lib/jenkins/jobs/workspace/netmd1.0/systemTest/createuser.txt http://192.168.1.99:8002/netmd/ws/ui/user/create -b cookies.txt >c.txt | if ! grep -q ""success":true,"error":null" ;then echo "exit1";fi`
#curl -H "Accept: application/json" -H "Content-type: application/json; charset=UTF-8" -X POST --data @/var/lib/jenkins/jobs/workspace/netmd1.0/systemTest/updateuser.txt http://192.168.1.99:8002/netmd/ws/ui/user/update -b cookies.txt >c.txt | if ! grep -q ""success":true,"error":null" ;then echo "exit1";fi`
#curl -H "Accept: application/json" -H "Content-type: application/json; charset=UTF-8" -X POST --data @/var/lib/jenkins/jobs/workspace/netmd1.0/systemTest/deleteuser.txt http://192.168.1.99:8002/netmd/ws/ui/user/delete -b cookies.txt >c.txt | if ! grep -q ""success":true,"error":null" ;then echo "exit1";fi`

#curl -H "Accept: application/json" -H "Content-type: application/json; charset=UTF-8" -X POST --data @/var/lib/jenkins/jobs/workspace/netmd1.0/systemTest/createadvance.txt http://192.168.1.99:8002/netmd/ws/ui/advance/create -b cookies.txt >c.txt | if ! grep -q ""success":true,"error":null" ;then echo "exit1";fi`
#curl -H "Accept: application/json" -H "Content-type: application/json; charset=UTF-8" -X POST --data @/var/lib/jenkins/jobs/workspace/netmd1.0/systemTest/updateadvance.txt http://192.168.1.99:8002/netmd/ws/ui/advance/update -b cookies.txt >c.txt | if ! grep -q ""success":true,"error":null" ;then echo "exit1";fi`
#curl -H "Accept: application/json" -H "Content-type: application/json; charset=UTF-8" -X POST --data @/var/lib/jenkins/jobs/workspace/netmd1.0/systemTest/advancelist.txt http://192.168.1.99:8002/netmd/ws/ui/advance/list -b cookies.txt >c.txt | if ! grep -q ""success":true,"error":null" ;then echo "exit1";fi`

curl -H "Accept: application/json" -H "Content-type: application/json; charset=UTF-8" -X POST --data @/var/lib/jenkins/jobs/workspace/netmd1.0/systemTest/createbill.txt http://192.168.1.99:8002/netmd/ws/ui/bill/create -b cookies.txt >c.txt | if ! grep -q ""success":true,"error":null" ;then echo "exit1";fi`
curl -H "Accept: application/json" -H "Content-type: application/json; charset=UTF-8" -X POST --data @/var/lib/jenkins/jobs/workspace/netmd1.0/systemTest/updatebillheader.txt http://192.168.1.99:8002/netmd/ws/ui/bill/updateBillHeader -b cookies.txt >c.txt | if ! grep -q ""success":true,"error":null" ;then echo "exit1";fi`
curl -H "Accept: application/json" -H "Content-type: application/json; charset=UTF-8" -X POST --data @/var/lib/jenkins/jobs/workspace/netmd1.0/systemTest/updatebillstatus.txt http://192.168.1.99:8002/netmd/ws/ui/bill/updateBillStatus -b cookies.txt >c.txt | if ! grep -q ""success":true,"error":null" ;then echo "exit1";fi`
curl -H "Accept: application/json" -H "Content-type: application/json; charset=UTF-8" -X POST --data @/var/lib/jenkins/jobs/workspace/netmd1.0/systemTest/updatebillitems.txt http://192.168.1.99:8002/netmd/ws/ui/bill/updateBillItems - b cookies.txt >c.txt | if ! grep -q ""success":true,"error":null" ;then echo "exit1";fi`
curl -H "Accept: application/json" -H "Content-type: application/json; charset=UTF-8" -X POST --data @/var/lib/jenkins/jobs/workspace/netmd1.0/systemTest/updatebillpayment.txt http://192.168.1.99:8002/netmd/ws/ui/bill/updateBillPayment -b cookies.txt >c.txt | if ! grep -q ""success":true,"error":null" ;then echo "exit1";fi`
curl -H "Accept: application/json" -H "Content-type: application/json; charset=UTF-8" -X POST --data @/var/lib/jenkins/jobs/workspace/netmd1.0/systemTest/billlist.txt http://192.168.1.99:8002/netmd/ws/ui/bill/list -b cookies.txt
curl -H "Accept: application/json" -H "Content-type: application/json; charset=UTF-8" -X POST --data @/var/lib/jenkins/jobs/workspace/netmd1.0/systemTest/updatebilldiscount.txt http://192.168.1.99:8002/netmd/ws/ui/bill/updateBillDiscount -b cookies.txt >c.txt | if ! grep -q ""success":true,"error":null" ;then echo "exit1";fi`

curl -H "Accept: application/json" -H "Content-type: application/json; charset=UTF-8" -X POST --data @/var/lib/jenkins/jobs/workspace/netmd1.0/systemTest/creatediscount.txt http://192.168.1.99:8002/netmd/ws/ui/discount/create-b cookies.txt >c.txt | if ! grep -q ""success":true,"error":null" ;then echo "exit1";fi`
curl -H "Accept: application/json" -H "Content-type: application/json; charset=UTF-8" -X POST --data @/var/lib/jenkins/jobs/workspace/netmd1.0/systemTest/updatebilldiscount.txt http://192.168.1.99:8002/netmd/ws/ui/discount/update-b cookies.txt >c.txt | if ! grep -q ""success":true,"error":null" ;then echo "exit1";fi`
curl -H "Accept: application/json" -H "Content-type: application/json; charset=UTF-8" -X POST --data @/var/lib/jenkins/jobs/workspace/netmd1.0/systemTest/listdiscount.txt http://192.168.1.99:8002/netmd/ws/ui/discount/list - b cookies.txt >c.txt | if ! grep -q ""success":true,"error":null" ;then echo "exit1";fi`
curl -H "Accept: application/json" -H "Content-type: application/json; charset=UTF-8" -X POST --data @/var/lib/jenkins/jobs/workspace/netmd1.0/systemTest/getdiscountvalue.txt http://192.168.1.99:8002/netmd/ws/ui/discount/getDiscountValue -b cookies.txt >c.txt | if ! grep -q ""success":true,"error":null" ;then echo "exit1";fi`

curl -H "Accept: application/json" -H "Content-type: application/json; charset=UTF-8" -X POST --data @/var/lib/jenkins/jobs/workspace/netmd1.0/systemTest/doctorlogin.txt http://192.168.1.99:8002/netmd/ws/ui/auth/doctorLogin -c cookies.txt >c.txt | if ! grep -q ""success":true,"error":null" ;then echo "exit1";fi`

curl -H "Accept: application/json" -H "Content-type: application/json; charset=UTF-8" -X POST --data @/var/lib/jenkins/jobs/workspace/netmd1.0/systemTest/createbed.txt http://192.168.1.99:8002/netmd/ws/ui/bed/create -b cookies.txt >c.txt | if ! grep -q ""success":true,"error":null" ;then echo "exit1";fi`
curl -H "Accept: application/json" -H "Content-type: application/json; charset=UTF-8" -X POST --data @/var/lib/jenkins/jobs/workspace/netmd1.0/systemTest/createbedtype.txt http://192.168.1.99:8002/netmd/ws/ui/bed/createBedType -b cookies.txt >c.txt | if ! grep -q ""success":true,"error":null" ;then echo "exit1";fi`
curl -H "Accept: application/json" -H "Content-type: application/json; charset=UTF-8" -X POST --data @/var/lib/jenkins/jobs/workspace/netmd1.0/systemTest/listbedtype.txt http://192.168.1.99:8002/netmd/ws/ui/bed/listBedType-b cookies.txt >c.txt | if ! grep -q ""success":true,"error":null" ;then echo "exit1";fi`
curl -H "Accept: application/json" -H "Content-type: application/json; charset=UTF-8" -X POST --data @/var/lib/jenkins/jobs/workspace/netmd1.0/systemTest/updatebedtype.txt http://192.168.1.99:8002/netmd/ws/ui/bed/updateBedType -b cookies.txt >c.txt | if ! grep -q ""success":true,"error":null" ;then echo "exit1";fi`
curl -H "Accept: application/json" -H "Content-type: application/json; charset=UTF-8" -X POST --data @/var/lib/jenkins/jobs/workspace/netmd1.0/systemTest/updatebed.txt http://192.168.1.99:8002/netmd/ws/ui/bed/update-b cookies.txt >c.txt | if ! grep -q ""success":true,"error":null" ;then echo "exit1";fi`
curl -H "Accept: application/json" -H "Content-type: application/json; charset=UTF-8" -X POST --data @/var/lib/jenkins/jobs/workspace/netmd1.0/systemTest/bedlist.txt http://192.168.1.99:8002/netmd/ws/ui/bed/list-b cookies.txt >c.txt | if ! grep -q ""success":true,"error":null" ;then echo "exit1";fi`


curl -H "Accept: application/json" -H "Content-type: application/json; charset=UTF-8" -X POST --data @/var/lib/jenkins/jobs/workspace/netmd1.0/systemTest/creatblock.txt http://192.168.1.99:8002/netmd/ws/ui/block/create-b cookies.txt >c.txt | if ! grep -q ""success":true,"error":null" ;then echo "exit1";fi`
curl -H "Accept: application/json" -H "Content-type: application/json; charset=UTF-8" -X POST --data @/var/lib/jenkins/jobs/workspace/netmd1.0/systemTest/updateblock.txt http://192.168.1.99:8002/netmd/ws/ui/block/update-b cookies.txt >c.txt | if ! grep -q ""success":true,"error":null" ;then echo "exit1";fi`
curl -H "Accept: application/json" -H "Content-type: application/json; charset=UTF-8" -X POST --data @/var/lib/jenkins/jobs/workspace/netmd1.0/systemTest/blocklist.txt http://192.168.1.99:8002/netmd/ws/ui/block/list-b cookies.txt >c.txt | if ! grep -q ""success":true,"error":null" ;then echo "exit1";fi`

curl -H "Accept: application/json" -H "Content-type: application/json; charset=UTF-8" -X POST --data @/var/lib/jenkins/jobs/workspace/netmd1.0/systemTest/createdept.txt http://192.168.1.99:8002/netmd/ws/ui/department/create-b cookies.txt >c.txt | if ! grep -q ""success":true,"error":null" ;then echo "exit1";fi`
curl -H "Accept: application/json" -H "Content-type: application/json; charset=UTF-8" -X POST --data @/var/lib/jenkins/jobs/workspace/netmd1.0/systemTest/updatedept.txt http://192.168.1.99:8002/netmd/ws/ui/department/update-b cookies.txt >c.txt | if ! grep -q ""success":true,"error":null" ;then echo "exit1";fi`
curl -H "Accept: application/json" -H "Content-type: application/json; charset=UTF-8" -X POST --data @/var/lib/jenkins/jobs/workspace/netmd1.0/systemTest/deptlist.txt http://192.168.1.99:8002/netmd/ws/ui/department/list-b cookies.txt >c.txt | if ! grep -q ""success":true,"error":null" ;then echo "exit1";fi`

curl -H "Accept: application/json" -H "Content-type: application/json; charset=UTF-8" -X POST --data @/var/lib/jenkins/jobs/workspace/netmd1.0/systemTest/createxpense.txt http://192.168.1.99:8002/netmd/ws/ui/expense/create-b cookies.txt >c.txt | if ! grep -q ""success":true,"error":null" ;then echo "exit1";fi`
curl -H "Accept: application/json" -H "Content-type: application/json; charset=UTF-8" -X POST --data @/var/lib/jenkins/jobs/workspace/netmd1.0/systemTest/expenselist.txt http://192.168.1.99:8002/netmd/ws/ui/expense/list-b cookies.txt >c.txt | if ! grep -q ""success":true,"error":null" ;then echo "exit1";fi`
curl -H "Accept: application/json" -H "Content-type: application/json; charset=UTF-8" -X POST --data @/var/lib/jenkins/jobs/workspace/netmd1.0/systemTest/updateexpense.txt http://192.168.1.99:8002/netmd/ws/ui/expense/update-b cookies.txt >c.txt | if ! grep -q ""success":true,"error":null" ;then echo "exit1";fi`

curl -H "Accept: application/json" -H "Content-type: application/json; charset=UTF-8" -X POST --data @/var/lib/jenkins/jobs/workspace/netmd1.0/systemTest/creathead.txt http://192.168.1.99:8002/netmd/ws/ui/head/create-b cookies.txt >c.txt | if ! grep -q ""success":true,"error":null" ;then echo "exit1";fi`
curl -H "Accept: application/json" -H "Content-type: application/json; charset=UTF-8" -X POST --data @/var/lib/jenkins/jobs/workspace/netmd1.0/systemTest/updatehead.txt http://192.168.1.99:8002/netmd/ws/ui/head/update-b cookies.txt >c.txt | if ! grep -q ""success":true,"error":null" ;then echo "exit1";fi`
curl -H "Accept: application/json" -H "Content-type: application/json; charset=UTF-8" -X POST --data @/var/lib/jenkins/jobs/workspace/netmd1.0/systemTest/headlist.txt http://192.168.1.99:8002/netmd/ws/ui/head/list-b cookies.txt >c.txt | if ! grep -q ""success":true,"error":null" ;then echo "exit1";fi`


curl -H "Accept: application/json" -H "Content-type: application/json; charset=UTF-8" -X POST --data @/var/lib/jenkins/jobs/workspace/netmd1.0/systemTest/creatitem.txt http://192.168.1.99:8002/netmd/ws/ui/item/create-b cookies.txt >c.txt | if ! grep -q ""success":true,"error":null" ;then echo "exit1";fi`
curl -H "Accept: application/json" -H "Content-type: application/json; charset=UTF-8" -X POST --data @/var/lib/jenkins/jobs/workspace/netmd1.0/systemTest/updateitem.txt http://192.168.1.99:8002/netmd/ws/ui/item/update-b cookies.txt >c.txt | if ! grep -q ""success":true,"error":null" ;then echo "exit1";fi`
curl -H "Accept: application/json" -H "Content-type: application/json; charset=UTF-8" -X POST --data @/var/lib/jenkins/jobs/workspace/netmd1.0/systemTest/updatelist.txt http://192.168.1.99:8002/netmd/ws/ui/item/list-b cookies.txt >c.txt | if ! grep -q ""success":true,"error":null" ;then echo "exit1";fi`

curl -H "Accept: application/json" -H "Content-type: application/json; charset=UTF-8" -X POST --data @/var/lib/jenkins/jobs/workspace/netmd1.0/systemTest/creatroom.txt http://192.168.1.99:8002/netmd/ws/ui/room/create-b cookies.txt >c.txt | if ! grep -q ""success":true,"error":null" ;then echo "exit1";fi`
curl -H "Accept: application/json" -H "Content-type: application/json; charset=UTF-8" -X POST --data @/var/lib/jenkins/jobs/workspace/netmd1.0/systemTest/updateroom.txt http://192.168.1.99:8002/netmd/ws/ui/room/update-b cookies.txt >c.txt | if ! grep -q ""success":true,"error":null" ;then echo "exit1";fi`
curl -H "Accept: application/json" -H "Content-type: application/json; charset=UTF-8" -X POST --data @/var/lib/jenkins/jobs/workspace/netmd1.0/systemTest/createroomtype.txt http://192.168.1.99:8002/netmd/ws/ui/room/createRoomType-b cookies.txt >c.txt | if ! grep -q ""success":true,"error":null" ;then echo "exit1";fi`
curl -H "Accept: application/json" -H "Content-type: application/json; charset=UTF-8" -X POST --data @/var/lib/jenkins/jobs/workspace/netmd1.0/systemTest/updateroomtype.txt http://192.168.1.99:8002/netmd/ws/ui/room/updateRoomType-b cookies.txt >c.txt | if ! grep -q ""success":true,"error":null" ;then echo "exit1";fi`
curl -H "Accept: application/json" -H "Content-type: application/json; charset=UTF-8" -X POST --data @/var/lib/jenkins/jobs/workspace/netmd1.0/systemTest/listroomtype.txt http://192.168.1.99:8002/netmd/ws/ui/room/listRoomType -b cookies.txt >c.txt | if ! grep -q ""success":true,"error":null" ;then echo "exit1";fi`
curl -H "Accept: application/json" -H "Content-type: application/json; charset=UTF-8" -X POST --data @/var/lib/jenkins/jobs/workspace/netmd1.0/systemTest/roomlist.txt http://192.168.1.99:8002/netmd/ws/ui/room/list -b cookies.txt >c.txt | if ! grep -q ""success":true,"error":null" ;then echo "exit1";fi`

curl -H "Accept: application/json" -H "Content-type: application/json; charset=UTF-8" -X POST --data @/var/lib/jenkins/jobs/workspace/netmd1.0/systemTest/creatsettings.txt http://192.168.1.99:8002/netmd/ws/ui/settings/create -b cookies.txt >c.txt | if ! grep -q ""success":true,"error":null" ;then echo "exit1";fi`
curl -H "Accept: application/json" -H "Content-type: application/json; charset=UTF-8" -X POST --data @/var/lib/jenkins/jobs/workspace/netmd1.0/systemTest/settinglist.txt http://192.168.1.99:8002/netmd/ws/ui/settings/list -b cookies.txt >c.txt | if ! grep -q ""success":true,"error":null" ;then echo "exit1";fi`
curl -H "Accept: application/json" -H "Content-type: application/json; charset=UTF-8" -X POST --data @/var/lib/jenkins/jobs/workspace/netmd1.0/systemTest/updatesettings.txt http://192.168.1.99:8002/netmd/ws/ui/settings/update -b cookies.txt >c.txt | if ! grep -q ""success":true,"error":null" ;then echo "exit1";fi`

curl -H "Accept: application/json" -H "Content-type: application/json; charset=UTF-8" -X POST --data @/var/lib/jenkins/jobs/workspace/netmd1.0/systemTest/creatservice.txt http://192.168.1.99:8002/netmd/ws/ui/support/create -b cookies.txt >c.txt | if ! grep -q ""success":true,"error":null" ;then echo "exit1";fi`
curl -H "Accept: application/json" -H "Content-type: application/json; charset=UTF-8" -X POST --data @/var/lib/jenkins/jobs/workspace/netmd1.0/systemTest/servicelist.txt http://192.168.1.99:8002/netmd/ws/ui/support/list -b cookies.txt >c.txt | if ! grep -q ""success":true,"error":null" ;then echo "exit1";fi`
curl -H "Accept: application/json" -H "Content-type: application/json; charset=UTF-8" -X POST --data @/var/lib/jenkins/jobs/workspace/netmd1.0/systemTest/updateservice.txt http://192.168.1.99:8002/netmd/ws/ui/support/update -b cookies.txt >c.txt | if ! grep -q ""success":true,"error":null" ;then echo "exit1";fi`

curl -H "Accept: application/json" -H "Content-type: application/json; charset=UTF-8" -X POST --data @/var/lib/jenkins/jobs/workspace/netmd1.0/systemTest/creattax.txt http://192.168.1.99:8002/netmd/ws/ui/tax/create -b cookies.txt >c.txt | if ! grep -q ""success":true,"error":null" ;then echo "exit1";fi`
curl -H "Accept: application/json" -H "Content-type: application/json; charset=UTF-8" -X POST --data @/var/lib/jenkins/jobs/workspace/netmd1.0/systemTest/updatetax.txt http://192.168.1.99:8002/netmd/ws/ui/tax/update -b cookies.txt >c.txt | if ! grep -q ""success":true,"error":null" ;then echo "exit1";fi`
curl -H "Accept: application/json" -H "Content-type: application/json; charset=UTF-8" -X POST --data @/var/lib/jenkins/jobs/workspace/netmd1.0/systemTest/listtax.txt http://192.168.1.99:8002/netmd/ws/ui/tax/list -b cookies.txt >c.txt | if ! grep -q ""success":true,"error":null" ;then echo "exit1";fi`



#curl  http://192.168.1.99:8002/netmd/ws/ui/advance/view/{id} -b cookies.txt >c.txt | if ! grep -q ""success":true,"error":null" ;then echo "exit1";fi`
#curl  http://192.168.1.99:8002/netmd/ws/ui/advance/delete/{id} -b cookies.txt >c.txt | if ! grep -q ""success":true,"error":null" ;then echo "exit1";fi`

curl  http://192.168.1.99:8002/netmd/ws/ui/bill/getlogo -b cookies.txt >c.txt | if ! grep -q ""success":true,"error":null" ;then echo "exit1";fi`
curl  http://192.168.1.99:8002/netmd/ws/ui/bill/view/{id} -b cookies.txt >c.txt | if ! grep -q ""success":true,"error":null" ;then echo "exit1";fi`
curl  http://192.168.1.99:8002/netmd/ws/ui/bill/discharge/{uid} -b cookies.txt >c.txt | if ! grep -q ""success":true,"error":null" ;then echo "exit1";fi`

curl  http://192.168.1.99:8002/netmd/ws/ui/appointment/delete/{id} -b cookies.txt >c.txt | if ! grep -q ""success":true,"error":null" ;then echo "exit1";fi`
curl  http://192.168.1.99:8002/netmd/ws/ui/appointment/view/{doctroId},{date} -b cookies.txt >c.txt | if ! grep -q ""success":true,"error":null" ;then echo "exit1";fi`

curl  http://192.168.1.99:8002/netmd/ws/ui/doctor/view/{id} -b cookies.txt >c.txt | if ! grep -q ""success":true,"error":null" ;then echo "exit1";fi`
curl  http://192.168.1.99:8002/netmd/ws/ui/doctor/delete/{id} -b cookies.txt >c.txt | if ! grep -q ""success":true,"error":null" ;then echo "exit1";fi`

curl  http://192.168.1.99:8002/netmd/ws/ui/message/viewMessage/{id} -b cookies.txt >c.txt | if ! grep -q ""success":true,"error":null" ;then echo "exit1";fi`

#curl  http://192.168.1.99:8002/netmd/ws/ui/owner/view/{id} -b cookies.txt >c.txt | if ! grep -q ""success":true,"error":null" ;then echo "exit1";fi`

curl  http://192.168.1.99:8002/netmd/ws/ui/patient/view/{id} -b cookies.txt >c.txt | if ! grep -q ""success":true,"error":null" ;then echo "exit1";fi`
curl  http://192.168.1.99:8002/netmd/ws/ui/patient/delete/{id} -b cookies.txt >c.txt | if ! grep -q ""success":true,"error":null" ;then echo "exit1";fi`
curl  http://192.168.1.99:8002/netmd/ws/ui/patient/listCase -b cookies.txt >c.txt | if ! grep -q ""success":true,"error":null" ;then echo "exit1";fi`
curl  http://192.168.1.99:8002/netmd/ws/ui/patient/listCaseByPatient/{patientId} -b cookies.txt >c.txt | if ! grep -q ""success":true,"error":null" ;then echo "exit1";fi`
curl  http://192.168.1.99:8002/netmd/ws/ui/patient/viewCase/{id} -b cookies.txt >c.txt | if ! grep -q ""success":true,"error":null" ;then echo "exit1";fi`
curl  http://192.168.1.99:8002/netmd/ws/ui/patient/listMedicalRecord/{patientId} -b cookies.txt >c.txt | if ! grep -q ""success":true,"error":null" ;then echo "exit1";fi`
curl  http://192.168.1.99:8002/netmd/ws/ui/patient/viewMedicalRecord/{id} -b cookies.txt >c.txt | if ! grep -q ""success":true,"error":null" ;then echo "exit1";fi`
curl  http://192.168.1.99:8002/netmd/ws/ui/patient/getMedicalRecordByCase/{patientId},{caseId} -b cookies.txt >c.txt | if ! grep -q ""success":true,"error":null" ;then echo "exit1";fi`
curl  http://192.168.1.99:8002/netmd/ws/ui/patient/listPersonalVisit/{patientId} -b cookies.txt >c.txt | if ! grep -q ""success":true,"error":null" ;then echo "exit1";fi`
curl  http://192.168.1.99:8002/netmd/ws/ui/patient/getPatients -b cookies.txt >c.txt | if ! grep -q ""success":true,"error":null" ;then echo "exit1";fi`

curl  http://192.168.1.99:8002/netmd/ws/ui/questionnaire/viewQuestionnaire/{id} -b cookies.txt >c.txt | if ! grep -q ""success":true,"error":null" ;then echo "exit1";fi`
curl  http://192.168.1.99:8002/netmd/ws/ui/questionnaire/deleteQuestionnaire/{id} -b cookies.txt >c.txt | if ! grep -q ""success":true,"error":null" ;then echo "exit1";fi`

#curl  http://192.168.1.99:8002/netmd/ws/ui/result/viewResult/{id} -b cookies.txt >c.txt | if ! grep -q ""success":true,"error":null" ;then echo "exit1";fi`

curl  http://192.168.1.99:8002/netmd/ws/ui/schedule/delete/{id} -b cookies.txt >c.txt | if ! grep -q ""success":true,"error":null" ;then echo "exit1";fi`
curl  http://192.168.1.99:8002/netmd/ws/ui/schedule/deleteInstance/{id} -b cookies.txt >c.txt | if ! grep -q ""success":true,"error":null" ;then echo "exit1";fi`
curl  http://192.168.1.99:8002/netmd/ws/ui/schedule/deleteFollowing/{id}/ -b cookies.txt >c.txt | if ! grep -q ""success":true,"error":null" ;then echo "exit1";fi`
curl  http://192.168.1.99:8002/netmd/ws/ui/schedule/dayView/{date},{doctorId} -b cookies.txt >c.txt | if ! grep -q ""success":true,"error":null" ;then echo "exit1";fi`
curl  http://192.168.1.99:8002/netmd/ws/ui/schedule/weekly/{date},{doctorId} -b cookies.txt >c.txt | if ! grep -q ""success":true,"error":null" ;then echo "exit1";fi`
curl  http://192.168.1.99:8002/netmd/ws/ui/schedule/monthly/{startDate},{endDate},{doctorId}} -b cookies.txt >c.txt | if ! grep -q ""success":true,"error":null" ;then echo "exit1";fi`

curl  http://192.168.1.99:8002/netmd/ws/ui/auth/lForm -b cookies.txt >c.txt | if ! grep -q ""success":true,"error":null" ;then echo "exit1";fi`
curl  http://192.168.1.99:8002/netmd/ws/ui/auth/getUser -b cookies.txt >c.txt | if ! grep -q ""success":true,"error":null" ;then echo "exit1";fi`
curl  http://192.168.1.99:8002/netmd/ws/ui/auth/getEnumsList -b cookies.txt >c.txt | if ! grep -q ""success":true,"error":null" ;then echo "exit1";fi`
curl  http://192.168.1.99:8002/netmd/ws/ui/auth/getErrorCodes -b cookies.txt >c.txt | if ! grep -q ""success":true,"error":null" ;then echo "exit1";fi`

curl  http://192.168.1.99:8002/netmd/ws/ui/bed/viewBedType/{id} -b cookies.txt >c.txt | if ! grep -q ""success":true,"error":null" ;then echo "exit1";fi`
curl  http://192.168.1.99:8002/netmd/ws/ui/bed/deleteBedType/{id} -b cookies.txt >c.txt | if ! grep -q ""success":true,"error":null" ;then echo "exit1";fi`
curl  http://192.168.1.99:8002/netmd/ws/ui/bed/view/{id} -b cookies.txt >c.txt | if ! grep -q ""success":true,"error":null" ;then echo "exit1";fi`
curl  http://192.168.1.99:8002/netmd/ws/ui/bed/deleteBed/{id} -b cookies.txt >c.txt | if ! grep -q ""success":true,"error":null" ;then echo "exit1";fi`
curl  http://192.168.1.99:8002/netmd/ws/ui/bed/getBeds -b cookies.txt >c.txt | if ! grep -q ""success":true,"error":null" ;then echo "exit1";fi`
curl  http://192.168.1.99:8002/netmd/ws/ui/bed/getBedTypes -b cookies.txt >c.txt | if ! grep -q ""success":true,"error":null" ;then echo "exit1";fi`


curl  http://192.168.1.99:8002/netmd/ws/ui/block/view/{id} -b cookies.txt >c.txt | if ! grep -q ""success":true,"error":null" ;then echo "exit1";fi`
curl  http://192.168.1.99:8002/netmd/ws/ui/block/delete/{id} -b cookies.txt >c.txt | if ! grep -q ""success":true,"error":null" ;then echo "exit1";fi`
curl  http://192.168.1.99:8002/netmd/ws/ui/block/getBlocks -b cookies.txt >c.txt | if ! grep -q ""success":true,"error":null" ;then echo "exit1";fi`


curl  http://192.168.1.99:8002/netmd/ws/ui/department/view/{id} -b cookies.txt >c.txt | if ! grep -q ""success":true,"error":null" ;then echo "exit1";fi`
curl  http://192.168.1.99:8002/netmd/ws/ui/department/delete/{id} -b cookies.txt >c.txt | if ! grep -q ""success":true,"error":null" ;then echo "exit1";fi`
curl  http://192.168.1.99:8002/netmd/ws/ui/department/getDepartments -b cookies.txt >c.txt | if ! grep -q ""success":true,"error":null" ;then echo "exit1";fi`

curl  http://192.168.1.99:8002/netmd/ws/ui/expense/view/{id} -b cookies.txt >c.txt | if ! grep -q ""success":true,"error":null" ;then echo "exit1";fi`
curl  http://192.168.1.99:8002/netmd/ws/ui/expense/delete/{id} -b cookies.txt >c.txt | if ! grep -q ""success":true,"error":null" ;then echo "exit1";fi`

curl  http://192.168.1.99:8002/netmd/ws/ui/head/view/{id} -b cookies.txt >c.txt | if ! grep -q ""success":true,"error":null" ;then echo "exit1";fi`
curl  http://192.168.1.99:8002/netmd/ws/ui/head/delete/{id} -b cookies.txt >c.txt | if ! grep -q ""success":true,"error":null" ;then echo "exit1";fi`
curl  http://192.168.1.99:8002/netmd/ws/ui/head/getHeads -b cookies.txt >c.txt | if ! grep -q ""success":true,"error":null" ;then echo "exit1";fi`

curl  http://192.168.1.99:8002/netmd/ws/ui/item/view/{id} -b cookies.txt >c.txt | if ! grep -q ""success":true,"error":null" ;then echo "exit1";fi`
curl  http://192.168.1.99:8002/netmd/ws/ui/item/delete/{id} -b cookies.txt >c.txt | if ! grep -q ""success":true,"error":null" ;then echo "exit1";fi`
curl  http://192.168.1.99:8002/netmd/ws/ui/item/getItems -b cookies.txt >c.txt | if ! grep -q ""success":true,"error":null" ;then echo "exit1";fi`

curl  http://192.168.1.99:8002/netmd/ws/ui/room/view/{id} -b cookies.txt >c.txt | if ! grep -q ""success":true,"error":null" ;then echo "exit1";fi`
curl  http://192.168.1.99:8002/netmd/ws/ui/room/delete/{id} -b cookies.txt >c.txt | if ! grep -q ""success":true,"error":null" ;then echo "exit1";fi`
curl  http://192.168.1.99:8002/netmd/ws/ui/room/viewRoomType/{id} -b cookies.txt >c.txt | if ! grep -q ""success":true,"error":null" ;then echo "exit1";fi`
curl  http://192.168.1.99:8002/netmd/ws/ui/room/deleteRoomType/{id} -b cookies.txt >c.txt | if ! grep -q ""success":true,"error":null" ;then echo "exit1";fi`
curl  http://192.168.1.99:8002/netmd/ws/ui/room/getRooms -b cookies.txt >c.txt | if ! grep -q ""success":true,"error":null" ;then echo "exit1";fi`
curl  http://192.168.1.99:8002/netmd/ws/ui/room/getRoomTypes -b cookies.txt >c.txt | if ! grep -q ""success":true,"error":null" ;then echo "exit1";fi`

curl  http://192.168.1.99:8002/netmd/ws/ui/settings/getByName/{name} -b cookies.txt >c.txt | if ! grep -q ""success":true,"error":null" ;then echo "exit1";fi`
curl  http://192.168.1.99:8002/netmd/ws/ui/settings/view/{id} -b cookies.txt >c.txt | if ! grep -q ""success":true,"error":null" ;then echo "exit1";fi`
curl  http://192.168.1.99:8002/netmd/ws/ui/settings/delete/{id}-b cookies.txt >c.txt | if ! grep -q ""success":true,"error":null" ;then echo "exit1";fi`

curl  http://192.168.1.99:8002/netmd/ws/ui/support/view/{id}-b cookies.txt >c.txt | if ! grep -q ""success":true,"error":null" ;then echo "exit1";fi`
curl  http://192.168.1.99:8002/netmd/ws/ui/support/delete/{id}-b cookies.txt >c.txt | if ! grep -q ""success":true,"error":null" ;then echo "exit1";fi`
curl  http://192.168.1.99:8002/netmd/ws/ui/support/getSupports-b cookies.txt >c.txt | if ! grep -q ""success":true,"error":null" ;then echo "exit1";fi`

curl  http://192.168.1.99:8002/netmd/ws/ui/tax/view/{id} -b cookies.txt >c.txt | if ! grep -q ""success":true,"error":null" ;then echo "exit1";fi`
curl  http://192.168.1.99:8002/netmd/ws/ui/tax/delete/{id}-b cookies.txt >c.txt | if ! grep -q ""success":true,"error":null" ;then echo "exit1";fi`
curl  http://192.168.1.99:8002/netmd/ws/ui/tax/getTaxes-b cookies.txt >c.txt | if ! grep -q ""success":true,"error":null" ;then echo "exit1";fi`



