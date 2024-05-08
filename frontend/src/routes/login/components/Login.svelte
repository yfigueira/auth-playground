<script lang="ts">
    import type { LoginForm } from "$lib/model/login-form";
    import { post } from "$lib/api";
    import Input from "$lib/form-elements/Input.svelte";
    import Button from "$lib/form-elements/Button.svelte";
    import {ALERT_TYPE, displayAlert} from "../../../stores/alertStore";
    import { goto } from "$app/navigation";
    import { login } from "../../../stores/tokenStore";

    export let formData: LoginForm;

    let emailValid: boolean = true;
    let passwordValid: boolean = true;

    async function handleSubmit() {
        validateEmail();
        validatePassword();

        if (!emailValid || !passwordValid) {
            return;
        }

        const response = await post("auth/login/username-password", formData);
        const responseBody = await response.json();

        if (!response.ok) {
            displayAlert(`[ ${errorResponse.status} ] ${errorResponse.causedBy}`, ALERT_TYPE.DANGER);
        } else {
            login(responseBody.token);
            await goto('/');
        }
    }

    function validateEmail(): void {
        let emailPattern = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;

        emailValid = formData.username !== ''
            && emailPattern.test(formData.username);
    }

    function validatePassword(): void {
        passwordValid = formData.password !== '';
    }

</script>

<form class="flex flex-col h-full justify-center items-center gap-8"
      on:submit|preventDefault={handleSubmit}>
    <Input label="Email"
           id="email"
           bind:value={formData.username}
           error={emailValid ? null : "Wrong format"}
           on:change={validateEmail}/>
    <Input label="Password"
           id="password"
           type="password"
           bind:value={formData.password}
           error={passwordValid ? null : "Password required"}
           on:change={validatePassword}/>
    <Button type="submit" className="mt-8 hover:bg-slate-700 hover:scale-105 transform transition-all duration-300" >Login</Button>
</form>

