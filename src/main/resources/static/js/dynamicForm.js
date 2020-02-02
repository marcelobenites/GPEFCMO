//Implementa para o exemplo test.html uma estratégia de adição de campos dinamica
var room = 1;
function education_fields() {
 
    room++;
    var objTo = document.getElementById('education_fields')
    var divtest = document.createElement("div");
	divtest.setAttribute("class", "form-group removeclass"+room);
	var rdiv = 'removeclass'+room;
    divtest.innerHTML = '<div class="panel panel-default"><div class="panel-body"><div id="education_fields"></div><div class="col-sm-3 nopadding"><div class="form-group"><div class="input-group"><select class="form-control" id="educationDate" name="educationDate[]"><option value="Selecione uma unidade"></option><option th:each="currentMUss : ${units}" th:value="${currentMUss.MU_name}" th:text="${currentMUss.MU_name}"></option></select><div class="input-group-btn"><button class="btn btn-success" type="button"  onclick="education_fields();"> <span class="fa fa-plus" aria-hidden="true"></span> </button></div></div></div></div></div>';
    
    objTo.appendChild(divtest)
}
   function remove_education_fields(rid) {
	   $('.removeclass'+rid).remove();
   }
