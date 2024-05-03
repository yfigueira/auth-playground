<script lang="ts">
    import Login from "./components/Login.svelte";
    import type { LoginForm } from "$lib/model/login-form";

    let formData: LoginForm = {
        username: '',
        password: ''
    }

</script>

<Login formData={formData} />