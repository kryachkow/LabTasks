const namePattern = /^\D[^@#!]{4,24}/;
const emailPattern = /^[\w-.]+@([\w-]+\.)+[\w-]{2,4}$/;
const phonePattern = /\d{3}-\d{3}-\d{4}/;

$(document).ready(function () {
  $('#form').submit(function (e) {

    e.preventDefault();
    let email = $('#email');
    let name = $('#name');
    let phone = $('#phone');
    let password = $('#password');
    let repeatPassword = $('#repeatPassword');
    let errorSpan = $('.error-span');
    console.log("I WORK")

    errorSpan.remove();


    if(!emailPattern.test(email.val())){
      email.after('<span class="error-span">Incorrect email!</span>');
    }

    if(!namePattern.test(name.val())){
      name.after('<span class="error-span">Incorrect name!</span>');
    }

    if(!phonePattern.test(phone.val())){
      phone.after('<span class="error-span">Incorrect phone!</span>');
    }

    if(password.val() !== repeatPassword.val){
      $('#table').after('<span class="error-span">Passwords don`t match!</span>');
    }

    $('.error-span').css('color', 'red');

  })
})