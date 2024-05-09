<script lang="ts">
    import Button from "$lib/form-elements/Button.svelte";
    import { goto } from "$app/navigation";
    import { logout } from "../stores/userStore";

    async function handleClick() {
        logout();
        await goto("/login");
    }
</script>


<h1 class="text-white text-3xl">Home Page</h1>
<Button on:click={handleClick}>Logout</Button>
