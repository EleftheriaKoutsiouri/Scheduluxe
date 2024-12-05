function toggleForms(formType) {
    const signInForm = document.querySelector('.sign-in-form');
    const signUpForm = document.querySelector('.sign-up-form');

    if (formType === 'sign-up') {
        signInForm.classList.remove('active');
        signUpForm.classList.add('active');
    } else if (formType === 'sign-in') {
        signUpForm.classList.remove('active');
        signInForm.classList.add('active');
    }
}