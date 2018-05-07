/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * patients module
 */
 // generate 2d data from json array function for q0405
 monthNames = [ "Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec" ];
 function data2Dq45(json_a){

group=[];
group_als=[];
sers=[];
// create x and y dimension
json_a.forEach(function (item){

testg=0;
group.forEach(function (gitem) {
if(gitem==item.to_date) testg=1;
});
if (testg==0) {

	group.push(item.to_date);
	
	
			
			group_als.push((item.Month_Text.charAt(0)=='[')? item.Month_Text+"-"+item.Year.toString().substr(2):monthNames[item.Month-1]+"-"+item.Year.toString().substr(2));
			//group_als.push(monthNames[item.Month-1]+"-"+item.Year.toString().substr(2));
	
}

tests=0;
sers.forEach(function (sitem) {
if(sitem==item.Request_Status) tests=1;
});
if (tests==0) sers.push(item.Request_Status);	

});

color_list=[ "rgb(199,214,238)", 
	 "rgb(149,182,223)",
	 "rgb(179,224,238)",
	  "rgb(199,214,238)", 
	 "rgb(149,182,223)",
	 "rgb(179,224,238)"];
icolor=0
all_sers=[];
sers.forEach(function (sitem) {
val_array1=new Array(group.length);
val_array1.fill(0);


ser_obj ={name: sitem, items:val_array1,    color:   ((sitem=="Denied")?"rgb(247,108,30)":((sitem=="No answer")?"rgb(251,201,142)":((sitem=="Approved")?"rgb(149,182,223)":((sitem=="Pending")?"rgb(221, 221, 221)":"rgb(179,224,238)"))))     };

json_a.forEach(function (item){
if(sitem==item.Request_Status) ser_obj.items[group.indexOf(item.to_date)]=ser_obj.items[group.indexOf(item.to_date)] + item["SumOfNumber of requests"];
});
all_sers.push(ser_obj);


});

return {group_array:group_als,ser_obj_array:all_sers};

}
// Function END ---generate 2d data from json array function 

/////////////////////////////////////
// generate 2d data from json array function for q06
 function data2Dq06(json_a){

groupq06=[];

val_array1=[];
val_array2=[];
val_array3=[];

// create x and y dimension
json_a.forEach(function (item){
groupq06.push(monthNames[item.Convoy_Month-1]+"-"+item.Convoy_Year.toString().substr(2));
val_array1.push(item.Requested_targeted_people);
val_array2.push(item.ApprovedBeneficiaries);
val_array3.push(item.Cumulative_Reached);

//add label
//val_array1.push ({value:item.Requested_targeted_people,label:item.Requested_targeted_people});
//val_array2.push ({value:item.ApprovedBeneficiaries,label:item.ApprovedBeneficiaries});
//val_array3.push ({value:item.Cumulative_Reached,label:item.Cumulative_Reached});
});

ser_objq06 =[{name: "Targeted people", items:val_array1,color:"rgb(219,206,190)"},
		     {name: "Approved", items:val_array2,color:"rgb(183,183,183)"},
	         {name: "Reached", items:val_array3,color:"rgb(120,120,120)"}];
 
 return {group_array:groupq06,ser_obj_array:ser_objq06};
}
// Function END ---generate 2d data from json array function 

/////////////////////////////////////
// generate 2d data from json array function for q06
 function data2Dq07(json_a){

groupq07=[];

val_array1=[];
val_array2=[];
val_array3=[];
val_array4=[];
val_array5=[];

// create x and y dimension
json_a.forEach(function (item){
groupq07.push(monthNames[item.Month_Number-1]+"-"+item.Year_Number.toString().substr(2));

val_array1.push(item.HTR_IAConvoys);
val_array2.push(item.HTR_UNRWA);
val_array3.push(item.Bsg_Air_Drops);
val_array4.push(item.Bsg_IAConvoys);
val_array5.push(item.Bsg_UNRWA);
});

ser_objq07 =[{name: "HTR IAConvoys", items:val_array1,color:"rgb(199,214,238)"},
		     {name: "HTR UNRWA", items:val_array2,color:"rgb(247,148,30)"},
		     {name: "Bsg Air Drops", items:val_array3,color:"rgb(149,182,223)"},
		     {name: "Bsg IAConvoys", items:val_array4,color:"rgb(251,201,142)"},
	         {name: "Bsg UNRWA", items:val_array5,color:"rgb(179,224,238)"}];
 
 return {group_array:groupq07,ser_obj_array:ser_objq07};
}
// Function END ---generate 2d data from json array function 
function addDays(date, days) {
    var result = new Date(date);
    result.setDate(result.getDate() + days);
    return result;
}
function yearStart(date) {
    var result = new Date(date);
    result.setDate(1);
	result.setMonth(0);
	
    return result;
}
define(['text!syria-htr-v2.json',
        'proj4',
        'ojs/ojcore',
        'knockout','jquery','ojs/ojchart','ojs/ojdialog',
        'ojs/ojthematicmap', 'promise', 'ojs/ojtable', 'ojs/ojarraytabledatasource', 'ojs/ojaccordion', 'ojs/ojcollapsible', 'ojs/ojradioset'],
        function (geo, proj4, oj, ko,$) {
            function mainContentViewModel() {
               var self = this; 
			   
              
				
				/*date converter */
				var options = {pattern: 'yyyy-MM-dd'}; 
                var converterFactory = oj.Validation.converterFactory("datetime");
                converter = converterFactory.createConverter(options);
                var formatted = converter.format(oj.IntlConverterUtils.dateToLocalIso(new Date()));
				
				self.dateConverter = oj.Validation.converterFactory(oj.ConverterFactory.CONVERTER_TYPE_DATETIME).
        createConverter(
        {
          pattern : 'yyyy-MM-dd'
        });
            
				
        this.value = ko.observable(oj.IntlConverterUtils.dateToLocalIso(yearStart(new Date())));
        this.toValue = ko.observable(oj.IntlConverterUtils.dateToLocalIso(new Date()));
  
                
                self.mapProvider = {
                    geo: JSON.parse(geo),
                    propertiesKeys: {
                        id: 'id',
                        shortLabel: 'sLabel',
                        longLabel: 'lLabel'
                    }
                };
                 storeLocations =[]; 
				  // verify that the component firing the event is a component of interest
				   this.mapOptionChange=function (event, ui) {
						//	console.log( ui);
						//	console.log( event);
							  if (ui['option'] == 'selection') {
									$.ajax({
							
											dataType: "json",
											url: "webapi/rs_q01_subpcode?q_from_date="+$("#from").val()+"&q_to_date="+$("#to").val()+"&q_pcode="+ui.value.pdl3["0"],
											success: function (movies) {
													// add data to the table
												movies.forEach(
														function (item){
															item.Total_Reach = Intl.NumberFormat().format(item.Total_Reach);
														}		
														);
													$("#okButton17").click(function() {$("#modalDialog17").ojDialog("close");});
													$("#modalDialog17").ojDialog("open");
													$("#table").ojTable("option", "data", new oj.ArrayTableDataSource(movies, {idAttribute: 'Convoy_Date'}));
													}
							  });
							}
					  };
                self.GetGeoData = function (self){ getDashboardData(self);};
                
      self.cities = [];          
self.cities;
            
            }
			setTimeout(function(self) {getDashboardData(self)}, 1000);
            return new mainContentViewModel();
        });
		
		
function getDashboardData (self) {
				
								//get data for fig 1 and 2  q02
								  $.ajax({
										   
											dataType: "json",
											url: "webapi/rs_q02_people_reached?q_from_date="+$("#from").val()+"&q_to_date="+$("#to").val(),
											success: function (fig1_2_data) {
													$("#fig1").text(Intl.NumberFormat().format(fig1_2_data[0].CnvyLocation_Total_Cumulative_Beneficiaries));
													$("#fig2").text(Intl.NumberFormat().format(fig1_2_data[0].Net_Reached));
												}
												,
												error: function () {
												 
												  $("#modalDialog1").ojDialog("open");
												  $("#okButton").click(function() {$("#modalDialog1").ojDialog("close");oj.Router.rootInstance.go('home'); });
												  
												}
										
								});	
								//get data for fig 3  q02
								  $.ajax({
										   
											dataType: "json",
											url: "webapi/rs_q02_htr_reached?q_from_date="+$("#from").val()+"&q_to_date="+$("#to").val(),
											success: function (fig_3_data) {
													$("#fig3").text(Intl.NumberFormat().format(fig_3_data[0].Total_Net_Reached_HTR));
													
												}
												,
												error: function () {
												 
												  $("#modalDialog1").ojDialog("open");
												  $("#okButton").click(function() {$("#modalDialog1").ojDialog("close");oj.Router.rootInstance.go('home'); });
												  
												}
										
								});	
								
								//get data for fig 4  q02
								  $.ajax({
										   
											dataType: "json",
											url: "webapi/rs_q02_besieged_reached?q_from_date="+$("#from").val()+"&q_to_date="+$("#to").val(),
											success: function (fig_4_data) {
													$("#fig4").text(Intl.NumberFormat().format(fig_4_data[0].Total_Net_Reached_Besieged));
													
												}
												,
												error: function () {
												 
												  $("#modalDialog1").ojDialog("open");
												  $("#okButton").click(function() {$("#modalDialog1").ojDialog("close");oj.Router.rootInstance.go('home'); });
												  
												}
										
								});	
								
									//get data for fig 5 q02
								  $.ajax({
										   
											dataType: "json",
											url: "webapi/rs_q02_xl_reached?q_from_date="+$("#from").val()+"&q_to_date="+$("#to").val(),
											success: function (fig_5_data) {
													$("#fig5").text(Intl.NumberFormat().format(fig_5_data[0].Total_Net_Reached_XL_Locations));
													
												}
												,
												error: function () {
												 
												  $("#modalDialog1").ojDialog("open");
												  $("#okButton").click(function() {$("#modalDialog1").ojDialog("close");oj.Router.rootInstance.go('home'); });
												  
												}
										
								});	
								
										//get data for fig 6 q02
								  $.ajax({
										   
											dataType: "json",
											url: "webapi/rs_q02_operations_bytype?q_from_date="+$("#from").val()+"&q_to_date="+$("#to").val(),
											success: function (fig_6_data) {
											
													//$("#fig6").text((fig_6_data[2].Total_Convoys_IA ));
													cctmp=0;
													fig_6_data.forEach(function (item){ if((item.Convoy_Type== 'Inter-Agency Convoy') ||  (item.Convoy_Type=='UNRWA'))  cctmp +=  item.Total_Convoys_IA; });											
													$("#fig6").text(Intl.NumberFormat().format(cctmp));
													
												}
												,
												error: function () {
												 
												  $("#modalDialog1").ojDialog("open");
												  $("#okButton").click(function() {$("#modalDialog1").ojDialog("close");oj.Router.rootInstance.go('home'); });
												  
												}
										
								});	
								
								
								
								
						//get data for map q01
								$.ajax({
					
									dataType: "json",
									url: "webapi/rs_q01?q_from_date="+$("#from").val()+"&q_to_date="+$("#to").val(),
									success: function (movies) {
													
													storeLocations	= movies;
													var basemap =$("#thematicmap1");
													var currentOptions = $("#thematicmap1").ojThematicMap("option");
													var cities = [];
													//self.cities111 = [];
													
													for (var i = 0; i < storeLocations.length; i++) {
																var store = storeLocations[i];
																var coords =[store.Longitude_WGS84, store.Latitude_WGS84];
																switch (store.Convoy_Type){
																case "Inter-Agency Convoy" : 
																m_icone ="Convoy.png";
																break;
																case "Air Drops" : 
																m_icone ="Airdrop.png";
																break;
																case "UNRWA" : 
																m_icone ="unrwa.png";
																break;
																m_icone ="Convoy.png";
																}
																cities.push({id: store.Community_PCODE, x: coords[0], y: coords[1], shortDesc: store.Location+" / "+store.Convoy_Type+" # "+store.total,labelPosition:"center" ,labelStyle :"color: yellow" ,label :store.total,value: 0.2 ,shape :"human" ,source :m_icone,opacity :0.5});

													}

													currentOptions['pointDataLayers'] = [{id: 'pdl3',selectionMode: 'single', markers: cities}];
													basemap.ojThematicMap("option", currentOptions);

 
													
																 
										}
										,
										error: function () {
										 
										  $("#modalDialog1").ojDialog("open");
										  $("#okButton").click(function() {$("#modalDialog1").ojDialog("close");oj.Router.rootInstance.go('home'); });
										  
										}
								
						});
						//get data for barChart ojchart and pie chart
                          $.ajax({
					               
									dataType: "json",
									url: "webapi/rs_q04_05?q_from_date="+$("#from").val()+"&q_to_date="+$("#to").val(),
									success: function (barChart_data) {
											var x2xjsonxdata = data2Dq45(barChart_data)	;
											percentConverter = { format: function(value) {  return value + ' ';            }        };
											$("#barChart").ojChart("option", "valueFormats", {   value: {converter: percentConverter}     });
											$("#barChart").ojChart("option", "series",x2xjsonxdata.ser_obj_array);
											$("#barChart").ojChart("option", "groups",x2xjsonxdata.group_array);

											arrx =[]
											x2xjsonxdata.ser_obj_array.forEach(function(xxx){
												
											x=xxx.items.reduce(function (total, num) {return total + num;});
											arrx.push({name: xxx.name, items:[x],color:xxx.color})
											 
											});
											 
											$("#pieChart").ojChart("option", "valueFormats", {   value: {converter: percentConverter}     });
											$("#pieChart").ojChart("option", "series",arrx);
											
																 
										}
										,
										error: function () {
										 
										  $("#modalDialog1").ojDialog("open");
										  $("#okButton").click(function() {$("#modalDialog1").ojDialog("close");oj.Router.rootInstance.go('home'); });
										  
										}
								
						});	
						//get data for barChart2 ojchart q06
                          $.ajax({
					               
									dataType: "json",
									url: "webapi/rs_q06?q_from_date="+$("#from").val()+"&q_to_date="+$("#to").val(),
									success: function (barChart_data) {
											var x2xjsonxdata = data2Dq06(barChart_data)	;
											$("#barChart2").ojChart("option", "series",x2xjsonxdata.ser_obj_array);
											$("#barChart2").ojChart("option", "groups",x2xjsonxdata.group_array);
																 
										}
										,
										error: function () {
										 
										  $("#modalDialog1").ojDialog("open");
										  $("#okButton").click(function() {$("#modalDialog1").ojDialog("close");oj.Router.rootInstance.go('home'); });
										  
										}
								
						});								
//get data for AreaChart  ojchart q07
                          $.ajax({
					               
									dataType: "json",
									url: "webapi/rs_q07?q_from_date="+$("#from").val()+"&q_to_date="+$("#to").val(),
									success: function (barChart_data) {
											var x2xjsonxdata = data2Dq07(barChart_data)	;
											$("#AreaChart").ojChart("option", "series",x2xjsonxdata.ser_obj_array);
											$("#AreaChart").ojChart("option", "groups",x2xjsonxdata.group_array);
																 
										}
										,
										error: function () {
										 
										  $("#modalDialog1").ojDialog("open");
										  $("#okButton").click(function() {$("#modalDialog1").ojDialog("close");oj.Router.rootInstance.go('home'); });
										  
										}
								
						});		
						

                 
				
        return true;
      };