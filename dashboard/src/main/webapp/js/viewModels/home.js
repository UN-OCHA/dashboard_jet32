/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/////getData Function
function getData(self) {
	
	
	
	
	
	//get data for text label
	  $.ajax({
			   
				dataType: "json",
				url: "webapi/rs_q02_ocha_dashboard_webtag",
				success: function (fig1_00_data) {
					 console.log(fig1_00_data);  
					$("#"+fig1_00_data[0].text_id).text(fig1_00_data[0].text_value)
					
					}
					,
					error: function () {
					 
					 // $("#modalDialog1").ojDialog("open");
					 // $("#okButton").click(function() {$("#modalDialog1").ojDialog("close");oj.Router.rootInstance.go('home'); });
					  
					}
			
	});	
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
												 
												 // $("#modalDialog1").ojDialog("open");
												 // $("#okButton").click(function() {$("#modalDialog1").ojDialog("close");oj.Router.rootInstance.go('home'); });
												  
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
												 
												 // $("#modalDialog1").ojDialog("open");
												 // $("#okButton").click(function() {$("#modalDialog1").ojDialog("close");oj.Router.rootInstance.go('home'); });
												  
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
												 
												//  $("#modalDialog1").ojDialog("open");
												//  $("#okButton").click(function() {$("#modalDialog1").ojDialog("close");oj.Router.rootInstance.go('home'); });
												  
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
												 
												//  $("#modalDialog1").ojDialog("open");
												//  $("#okButton").click(function() {$("#modalDialog1").ojDialog("close");oj.Router.rootInstance.go('home'); });
												  
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
												 
											//	  $("#modalDialog1").ojDialog("open");
											//	  $("#okButton").click(function() {$("#modalDialog1").ojDialog("close");oj.Router.rootInstance.go('home'); });
												  
												}
										
								});	
								
								
								
								
						//get data for map q01
								$.ajax({
					
									dataType: "json",
									url: "webapi/rs_q01?q_from_date="+$("#from").val()+"&q_to_date="+$("#to").val(),
									success: function (movies) {
													
													storeLocations	= movies;
													var basemap =$("#thematicmap1h");
													var currentOptions = $("#thematicmap1h").ojThematicMap("option");
													var citiesh = [];
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
																citiesh.push({id: store.Community_PCODE, x: coords[0], y: coords[1], shortDesc: store.Location+" / "+store.Convoy_Type+" # "+store.total,labelPosition:"center" ,labelStyle :"color: yellow" ,label :store.total,value: 0.2 ,shape :"human" ,source :m_icone,opacity :0.5});

													}

													currentOptions['pointDataLayers'] = [{id: 'pdl3',selectionMode: 'single', markers: citiesh}];
													basemap.ojThematicMap("option", currentOptions);

 
													
																 
										}
										,
										error: function () {
										 
										 // $("#modalDialog1").ojDialog("open");
										 // $("#okButton").click(function() {$("#modalDialog1").ojDialog("close");oj.Router.rootInstance.go('home'); });
										  
										}
								
						});
						 
						 								
 
						

                 
				
        return true;
      };

	  
//end of getdata function

	  
/**
 * home module
 */
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
 function getCookie(cname) {
    var name = cname + "=";
    var decodedCookie = decodeURIComponent(document.cookie);
    var ca = decodedCookie.split(';');
    for(var i = 0; i <ca.length; i++) {
        var c = ca[i];
        while (c.charAt(0) == ' ') {
            c = c.substring(1);
        }
        if (c.indexOf(name) == 0) {
            return c.substring(name.length, c.length);
        }
    }
    return "";
}
function setCookie(cname, cvalue, exdays) {
    var d = new Date();
    d.setTime(d.getTime() + (exdays*24*60*60*1000));
    var expires = "expires="+d.toUTCString();
    document.cookie = cname + "=" + cvalue + "; " + expires +";path=/";
}
//define(['ojs/ojcore', 'knockout','ojs/ojchart', 'ojs/ojdialog'
define(['text!syria-htr-v2.json',
        'proj4',
        'ojs/ojcore',
        'knockout','jquery','promise','ojs/ojdialog',
        'ojs/ojthematicmap',  'ojs/ojtable', 'ojs/ojarraytabledatasource'
], function (geo, proj4, oj, ko,$,prms) {
    /**
     * The view model for the main content view template
     */
    function homeContentViewModel() {
				
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
            
			
			var tmpDate =new Date();
		//	tmpDate.setDate(1);
		//	tmpDate.setMonth(0);
				
        this.value = ko.observable(oj.IntlConverterUtils.dateToLocalIso(yearStart(new Date())));
        this.toValue = ko.observable(oj.IntlConverterUtils.dateToLocalIso(new Date()));
  
                
                self.mapProviderh = {
                    geo: JSON.parse(geo),
                    propertiesKeys: {
                        id: 'id',
                        shortLabel: 'sLabel',
                        longLabel: 'lLabel'
                    }
                };
                 storeLocations =[]; 
				  // verify that the component firing the event is a component of interest
				   this.mapOptionChangeh=function (event, ui) {
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
		 
         
		 self.GetGeoData = function(self) {
							getData(self);
										
        return true;
      };
       
      self.citiesh = [];          
self.citiesh;
	
	
    self.Getlogin = function() {
	
									$.ajax({ 
															beforeSend: function (request) {
																request.setRequestHeader('ocha_user',$("#uname").val());
																request.setRequestHeader('ocha_user_pw',$("#upassword").val());
															},
															url: "webapi/rs/login",
															success: function (movies,xxx,repons) {
																 
																 self.userLogin = ko.observable($("#uname").val());
																oj.Router.rootInstance.go('patients');
																}
																,
																error: function (movies, textStatus, errorThrown) {
																 
																 
																 
																  $("#modalDialog1").ojDialog("open");
																  $("#okButton").click(function() {$("#modalDialog1").ojDialog("close"); });
																}
																
														});
										
									};
    ///get data to the site
	setTimeout(function(self) {getData(self)}, 1000);
	 
 
	}
    
    return homeContentViewModel;
});

