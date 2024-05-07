<script>
    import Button from "$lib/form-elements/Button.svelte";
    import { goto } from "$app/navigation";
    async function handleClick() {
        localStorage.removeItem("token");
        await goto("/login");
    }
</script>

<h1 class="text-white">Home Page</h1>
<Button on:click={handleClick}>Logout</Button>

