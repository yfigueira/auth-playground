<script lang="ts">
    import "../app.css"
    import michael from "$lib/assets/Michael-Jackson.svg"
    import Alert from "./Alert.svelte";
    import { userToken } from "../stores/userStore";
    import { blur } from "svelte/transition";

</script>

<div class="flex flex-row h-screen w-screen">
    <div class="h-full w-full justify-center items-center">
        {#if $userToken}
            <img src={michael} alt="Michael Jackson picture" class="h-full w-full" transition:blur={{amount: 20, duration: 600}} />
        {/if}
    </div>
    <div class="bg-slate-800 h-full w-full">
        <Alert />
        <slot />
    </div>
</div>