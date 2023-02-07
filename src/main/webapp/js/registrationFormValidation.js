
  const form = document.querySelector('#form');
  const name = document.querySelector('#name');
  const email = document.querySelector('#email');
  const phone = document.querySelector('#phone');
  const password = document.querySelector('#password');
  const repeatPassword = document.querySelector('#repeatPassword');

  const nameError = document.querySelector('#nameError');
  const emailError = document.querySelector('#emailError');
  const phoneError = document.querySelector('#phoneError');
  const passwordError = document.querySelector('#passwordError');

  const namePattern = /^\D[^@#!]{4,24}/;
  const emailPattern = /^[\w-.]+@([\w-]+\.)+[\w-]{2,4}$/;
  const phonePattern = /\d{3}-\d{3}-\d{4}/;


  form.onsubmit = function () {
    cleanUpBorders();
    cleanErrors();
    let flag = true;

    if(!emailPattern.test(email.value)){
      manageInputField(email);
      setError(emailError, 'Incorrect email!');
      flag = false;
    }

    if(!namePattern.test(name.value)){
      manageInputField(name);
      setError(nameError, 'Incorrect name!');
      flag = false;
    }

    if(!phonePattern.test(phone.value)){
      manageInputField(phone);
      setError(phoneError, 'Incorrect phone!')
      flag = false;
    }

    if (password.value !== repeatPassword.value) {
      manageInputField(password);
      manageInputField(repeatPassword);
      setError(passwordError, 'Passwords don`t match!');
      flag = false;
    }

    if(flag){
      return flag;
    }
    password.value = '';
    repeatPassword.value = '';
    return flag;
  }

  function manageInputField(element, message) {
    element.value = '';
    element.style.borderColor = 'red';
  }

  function setError(element, message){
    element.innerText = message;
    element.style.color = 'red';
  }

  function cleanUpBorders(){
    let inputs = form.querySelectorAll("input.form-control");
    inputs.forEach(function (currentValue, currentIndex,list) {
      currentValue.style.borderColor = '';
    })
  }

  function cleanErrors(){
    let errors = form.querySelectorAll("div.error-div");
    errors.forEach(function (currentValue, currentIndex,list) {
      currentValue.innerText = '';
      currentValue.style.color = '';
    })
  }

