<html>
<head>
</head>
<body>
[@ww.textfield class="text long-field " labelKey="helloworld.tenantID" name="tenantid" required='true'/]
[@ww.select list=listOfAZURETemplates toggle="true"  onchange='javascript: loadSingleTemplateParams(this.value)' id="azureid" label="List Of Scripts" listKey='value' listValue='value' name="azuretemplate" /]
</body>
</html>


<script type="text/javascript">

function loadSingleTemplateParams(myvalue){
var container = document.getElementById("sorbhs");
while (container.hasChildNodes()) {
                container.removeChild(container.lastChild);
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
			var el2 = document.getElementById("jsonpersistid");
   			el2.value=p;
					}
				}
  	}, complete: function (data) {
  	 var x=document.getElementById("saveparamvaluesid");
  		var savekeys = document.createElement('input');
 	 	 savekeys.value = paramKeys;
  		 savekeys.className = "text text";
  		 savekeys.type = "text";
		savekeys.id = "saveparamkeysid";
		savekeys.name = "saveparamkeysid";
   		 x.appendChild(savekeys);
   		 
  		var savevalues = document.createElement('input');
 	 	 savevalues.value = paramValues;
  		 savevalues.className = "text text";
  		 savevalues.type = "text";
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
