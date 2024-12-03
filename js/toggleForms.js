function toggleForms(formType) {
    const signInForm = document.querySelector('.sign-in-form');
    const signUpForm = document.querySelector('.sign-up-form');
    
    if (formType === 'sign-up') {
        signInForm.style.display = 'none'; 
        signUpForm.style.display = 'block'; 
    } else {
        signUpForm.style.display = 'none'; 
        signInForm.style.display = 'block';
    }
}
