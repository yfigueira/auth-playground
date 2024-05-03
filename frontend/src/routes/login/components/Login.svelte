<script lang="ts">
    import type { LoginForm } from "$lib/model/login-form";
    import Input from "$lib/form-elements/Input.svelte";
    import Button from "$lib/form-elements/Button.svelte";

    export let formData: LoginForm;

    let emailValid: boolean = true;
    let passwordValid: boolean = true;

    function handleSubmit() {
        validateEmail();
        validatePassword();

        if (!emailValid || !passwordValid) {
            return;
        } else {
            console.log(formData)
        }
    }

    function validateEmail(): void {
        let emailPattern = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;

        emailValid = formData.email !== ''
            && emailPattern.test(formData.email);
    }

    function validatePassword(): void {
        passwordValid = formData.password !== '';
    }

</script>

<form class="flex flex-col h-full justify-center items-center gap-8"
      on:submit|preventDefault={handleSubmit}>
    <Input label="Email"
           id="email"
           bind:value={formData.email}
           error={emailValid ? null : "Wrong format"}
           on:change={validateEmail}/>
    <Input label="Password"
           id="password"
           bind:value={formData.password}
           error={passwordValid ? null : "Password required"}
           on:change={validatePassword}/>
    <Button type="submit" className="mt-8 hover:bg-slate-700 hover:scale-105 transform transition-all duration-300" >Login</Button>
</form>

