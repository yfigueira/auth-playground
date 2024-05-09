<script lang="ts">
    import Button from "$lib/form-elements/Button.svelte";
    import { goto } from "$app/navigation";
    import { userToken, userId, logout } from "../stores/userStore";
    import { onMount } from "svelte";
    import type { UserData } from "$lib/model/user-data";
    import { get } from "$lib/api";

    let userData: UserData = {};

    onMount(async () => {
        let response = await get(`users/${$userId}`, $userToken);
        userData = await response.json();
    })

    async function handleClick() {
        logout();
        await goto("/login");
    }

    function format({firstName, lastName, email}: UserData) {
        return `${firstName} ${lastName} ${email}`
    }
</script>


<h1 class="text-white text-3xl">{format(userData)}</h1>
<Button on:click={handleClick}>Logout</Button>
