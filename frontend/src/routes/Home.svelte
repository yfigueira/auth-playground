<script lang="ts">
    import { goto } from "$app/navigation";
    import { userToken, userId, logout } from "../stores/userStore";
    import { onMount } from "svelte";
    import type { UserData } from "$lib/model/user-data";
    import { get } from "$lib/api";
    import Fa from "svelte-fa";
    import { faGithub, faLinkedin } from "@fortawesome/free-brands-svg-icons";
    import { faArrowRightFromBracket } from "@fortawesome/free-solid-svg-icons";

    let userData: UserData = {};

    onMount(async () => {
        let response = await get(`users/${$userId}`, $userToken);
        userData = await response.json();
    })

    async function handleClick() {
        logout();
        await goto("/login");
    }

</script>

<div class="flex flex-col h-full justify-center items-center gap-4 text-white">
    <span class="text-8xl">Welcome</span>
    <hr class="m-12" />
    <span class="text-4xl">{`${userData.firstName} ${userData.lastName}`}</span>
    <hr class="w-1/2" />
    <span class="text-xl">{`${userData.email}`}</span>
    <hr class="m-4" />
    <div class="flex flex-row items-center gap-8">
        <span class="text-lg">visit:</span>
        <span><a href="https://github.com/yfigueira"><Fa icon={faGithub} class="text-4xl hover:scale-105" /></a></span>
        <span><a href="https://www.linkedin.com/in/yohann-figueira-putresza-364876210/"><Fa icon={faLinkedin} class="text-4xl hover:scale-105" /></a></span>
    </div>
    <hr class="m-12" />
    <button class="text-4xl hover:scale-110" on:click={handleClick}><Fa icon={faArrowRightFromBracket} /></button>
</div>
