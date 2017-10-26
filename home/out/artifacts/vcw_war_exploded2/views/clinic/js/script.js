//Function for validating form in ClinicCreateUserServlet
// name.trim('') || name.toLowerCase().localeCompare("null") ||
function validateUserForm(){
    var name= $('#name').val();

    if(name.trim() == '' || name.toLowerCase()== 'null' || !isNaN(parseInt(name,10)) ){
        alert("Вы ввели недопустимое имя пользователя! " + name
            + "\nВведите правильное имя и повторите попытку! ");
        return false;
    }
    //return true;
}