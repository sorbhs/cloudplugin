<html>
<head>

<script type="text/javascript">
        function codeAddress() {
       var scloudprovider='${cloudprovider}';
       if(scloudprovider=="AWS")
      {
       var pojoKeys='${parameterPOJO.paramKeys}';
       var pojoValues='${parameterPOJO.paramValues}';
       var container = document.getElementById("sorbhs");
while (container.hasChildNodes()) {
                container.removeChild(container.lastChild);
            }
     var paramKeys = [];
var paramValues=[];	
var BASE_URL = '${baseUrl}';
 AJS.$.ajax({
 	url: BASE_URL+"/plugins/servlet/fetchparametersdata?paramObjectKeys="+pojoKeys+"&paramObjectValues="+pojoValues,
 	dataType: 'json',
 	cache: false,
  success: function(p){
for (var key in p) {
if(p.hasOwnProperty(key)) {
container.appendChild(document.createTextNode(key));
var input = document.createElement("input");
input.type = "text";
input.name = key;
paramKeys.push(key);
input.className = "text text";
input.defaultValue=p[key];
paramValues.push(p[key]);
container.appendChild(input);
container.appendChild(document.createElement("br"));
}
}
  },
  complete: function (data) {
  	 var x=document.getElementById("testfail");
  	 while (x.hasChildNodes()) {
                x.removeChild(x.lastChild);
          						  }
  		var savekeys = document.createElement('input');
 	 	 savekeys.value = paramKeys;
  		 savekeys.className = "text text";
  		 savekeys.type = "text";
  		 savekeys.type = 'hidden';
		savekeys.id = "saveparamkeysid";
		savekeys.name = "saveparamkeysid";
   		 x.appendChild(savekeys);
   		 
  		var savevalues = document.createElement('input');
 	 	 savevalues.value = paramValues;
  		 savevalues.className = "text text";
  		 savevalues.type = "text";
  		 savevalues.type = 'hidden';
		savevalues.id = "saveparamvaluesid";
		savevalues.name = "saveparamvaluesid";
   		x.appendChild(savevalues);
     },
   error: function () {
					alert("failed");
                        }
   });
       }
       else{
       var kchc='${project_version}';
       alert(kchc);
       var pojoKeys='${parameterPOJO.paramKeys}';
       var pojoValues='${parameterPOJO.paramValues}';
              var container = document.getElementById("sorbhs");
while (container.hasChildNodes()) {
                container.removeChild(container.lastChild);
          						  }
    var paramKeys = [];
var paramValues=[];						  
    var BASE_URL = '${baseUrl}';
     AJS.$.ajax({
 	url:  BASE_URL+"/plugins/servlet/fetchparametersdata?paramObjectKeys="+pojoKeys+"&paramObjectValues="+pojoValues,
 	dataType: 'json',
 	cache: false,
  success: function(p){
for (var key in p) {
if(p.hasOwnProperty(key)) {
container.appendChild(document.createTextNode(key));
var input = document.createElement("input");
input.type = "text";
input.name = key;
paramKeys.push(key);
input.className = "text text";
input.defaultValue=p[key];
paramValues.push(p[key]);
container.appendChild(input);
container.appendChild(document.createElement("br"));
}
}
  },
   complete: function (data) {
  	 var x=document.getElementById("testfail");
  	 while (x.hasChildNodes()) {
                x.removeChild(x.lastChild);
          						  }
  	 
  		var savekeys = document.createElement('input');
 	 	 savekeys.value = paramKeys;
  		 savekeys.className = "text text";
  		 savekeys.type = "text";
		savekeys.id = "saveparamkeysid";
		 savekeys.type = 'hidden';
		savekeys.name = "saveparamkeysid";
   		 x.appendChild(savekeys);
   		 
  		var savevalues = document.createElement('input');
 	 	 savevalues.value = paramValues;
  		 savevalues.className = "text text";
  		 savevalues.type = "text";
  		  savevalues.type = 'hidden';
		savevalues.id = "saveparamvaluesid";
		savevalues.name = "saveparamvaluesid";
   		x.appendChild(savevalues);
     },
   error: function () {
					alert("failed");
                        }
   }); 	   
     	   }
       
      }
        codeAddress();
 </script>
        
</head>

<body >
[@ww.select list=operation toggle="true"  label="Operation" listKey='value' listValue='value' name="myoperation" /]

[@ww.select list=region toggle="true"  label="Region" listKey='value' listValue='value' name="cloudregion" /]

[@ww.textfield tooltip="Enter the customer name" class="text long-field " labelKey="helloworld.clientID" name="clientid" required='true'/]

[@ww.textfield class="text long-field " labelKey="helloworld.clientSecret" name="clientsecret" required='true'/]

[@ww.textfield class="text long-field " labelKey="helloworld.tenantID" name="tenantid" required='true'/]

[@ww.textfield class="text long-field " labelKey="helloworld.subscriptionID" name="subscriptionid" /]

[@ww.textfield class="text long-field " labelKey="helloworld.resourceGroupName" name="resourcegroupname" required='true'/]

[@ww.textfield  class="text long-field "labelKey="helloworld.deploymentName" name="deploymentname" required='true'/]

[@ww.select list=cloudProvider toggle="true"  label="Cloud Provider"   onchange='javascript: clearparams()' listKey='value' listValue='value' name="cloudprovider" /]

[@ui.bambooSection   dependsOn='cloudprovider' showOn='AWS']
  
  [@ww.select list=listOfAWSTemplates toggle="true"  onchange='javascript: loadSingleTemplateParams(this.value)'   id='awsid' label="List Of Scripts" listKey='value' listValue='value' name="awstemplate" /]
 	
[/@ui.bambooSection]
 
[@ui.bambooSection dependsOn='cloudprovider' showOn='Azure']
  
  [@ww.select list=listOfAZURETemplates toggle="true"  onchange='javascript: loadSingleTemplateParams(this.value)' id="azureid" label="List Of Scripts" listKey='value' listValue='value' name="azuretemplate" /]

[/@ui.bambooSection]
 
[@ww.hidden name="hiddenname" value="true" /]

 <div id="phonediv2">
 </div>
 
 <div id="savekeysvalues">
 </div>
  
<div id="sorbhs">
</div>  

<div id="testfail">
</div> 
</body>
<div id="sorbhs2">
</div> 

</body>

<script type="text/javascript">
function clearparams(){
var container = document.getElementById("sorbhs");
 while (container.hasChildNodes()) {
                container.removeChild(container.lastChild);
            }
}

function loadTemplateData(myvalue,abcv){
var container = document.getElementById("params");
 while (container.hasChildNodes()) {
                container.removeChild(container.lastChild);
            }
for(var j=0;j<abcv.size	;j++){
if(myvalue==abcv.templates[j].name){
for (var key in abcv.templates[j].parameters) {
if(abcv.templates[j].parameters.hasOwnProperty(key)) {
    
container.appendChild(document.createTextNode(key));
var input = document.createElement("input");
input.type = "text";
input.name = key;
input.className = "text text";
input.defaultValue=abcv.templates[j].parameters[key];
container.appendChild(input);
container.appendChild(document.createElement("br"));
}
}}
}}

function loadSingleTemplateParams(myvalue){
var container = document.getElementById("sorbhs");
while (container.hasChildNodes()) {
                container.removeChild(container.lastChild);
            }
            var x=document.getElementById("testfail");
            while (x.hasChildNodes()) {
                x.removeChild(x.lastChild);
          						  }
var BASE_URL = '${baseUrl}';
AJS.$('#loading-image').show();
var paramKeys = [];
var paramValues=[];
 AJS.$.ajax({
 	url:  BASE_URL+"/plugins/servlet/helloworld?templateName="+myvalue,
 	dataType: 'json',
 	cache: false,
  	success: function(p){
			for (var key in p) {
			if(p.hasOwnProperty(key)) {
			container.appendChild(document.createTextNode(key));
			var input = document.createElement("input");
			input.type = "text";
			input.name = key;
			input.id = key;
			paramKeys.push(key);
			input.className = "text text";
			input.value=p[key];
			paramValues.push(p[key]);
			container.appendChild(input);
			container.appendChild(document.createElement("br"));
					}
				}
  	}, complete: function (data) {
  	 var x=document.getElementById("testfail");
  		var savekeys = document.createElement('input');
 	 	 savekeys.value = paramKeys;
  		 savekeys.className = "text text";
  		 savekeys.type = "text";
		savekeys.id = "saveparamkeysid";
		savekeys.name = "saveparamkeysid";
		savekeys.type = 'hidden';
   		 x.appendChild(savekeys);
   		 
  		var savevalues = document.createElement('input');
 	 	 savevalues.value = paramValues;
  		 savevalues.className = "text text";
  		 savevalues.type = "text";
  		 savevalues.type = 'hidden';
		savevalues.id = "saveparamvaluesid";
		savevalues.name = "saveparamvaluesid";
   		x.appendChild(savevalues);
     },
    error: function () {
			alert("failed");
    }
  });
}


</script>
