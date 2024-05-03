<script lang="ts">
    import Input from "$lib/form-elements/Input.svelte";
    import type { RegistrationForm } from "$lib/model/registration-form";
    import Button from "$lib/form-elements/Button.svelte";
    import { ALERT_TYPE, displayAlert } from "../../../stores/alertStore";
    import { goto } from '$app/navigation';

    export let formData: RegistrationForm;

    let passwordMatch: boolean = true;
    let passwordValid: boolean = true;
    let emailValid: boolean = true;
    let firstNameValid: boolean = true;
    let lastNameValid: boolean = true;

    async function handleSubmit(): void {
        validateEmail();
        validatePassword();
        validateFirstName();
        validateLastName();

        if (!passwordValid || !passwordMatch || !emailValid || !firstNameValid || !lastNameValid) {
            return;
        } else {
            const response = await fetch("http://localhost:8080/api/auth/register", {
                method: "POST",
                headers: {
                    "Content-Type": "application/json"
                },
                body: JSON.stringify(formData)
            });

            if (!response.ok) {
                let errorResponse = await response.json();
                displayAlert(`[ ${errorResponse.status} ] ${errorResponse.causedBy}`, ALERT_TYPE.DANGER);
            } else {
                displayAlert("Registration successful", ALERT_TYPE.SUCCESS);
                await goto('/login');
            }
        }
    }

    function comparePasswords(): void {
        passwordMatch = formData.password === formData.repeatedPassword;
    }

    function validatePassword(): void {
        passwordValid = formData.password !== '';
        comparePasswords();
    }

    function validateEmail(): void {
        let emailPattern = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;

        emailValid = formData.email !== ''
            && emailPattern.test(formData.email);
    }

    function validateFirstName(): void {
        firstNameValid = formData.firstName !== '';
    }

    function validateLastName(): void {
        lastNameValid = formData.lastName !== '';
    }

</script>

<form class="flex flex-col h-full justify-center items-center gap-8"
      on:submit|preventDefault={handleSubmit}>
    <Input label="First name"
           id="firstname"
           bind:value={formData.firstName}
           error={firstNameValid ? null : "First name required"}
           on:change={validateFirstName} />
    <Input label="Last name"
           id="lastname"
           bind:value={formData.lastName}
           error={lastNameValid ? null : "Last name required"}
           on:change={validateLastName} />
    <Input label="Email"
           id="email"
           bind:value={formData.email}
           error={emailValid ? null : "Wrong format"}
           on:change={validateEmail}/>
    <Input label="Password"
           type="password"
           id="password"
           bind:value={formData.password}
           error={passwordValid ? null : "Password required"}
           on:change={validatePassword}/>
    <Input label="Repeat password"
           type="password"
           id="repeat-password"
           bind:value={formData.repeatedPassword}
           error={passwordMatch ? null : "Passwords must match"}
           on:change={validatePassword}/>
    <Button type="submit" className="mt-8 hover:bg-slate-700 hover:scale-105 transform transition-all duration-300">Register</Button>
</form>