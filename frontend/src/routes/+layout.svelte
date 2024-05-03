<script lang="ts">
    import "../app.css"
    import michael from "$lib/assets/Michael-Jackson.svg"
    import Alert from "./Alert.svelte";
</script>

<div class="flex flex-row h-screen w-screen">
    <div class="h-full w-full justify-center items-center">
        <img src={michael} alt="Michael Jackson picture" class="h-full w-full" />
    </div>
    <div class="bg-slate-800 h-full w-full">
        <Alert />
        <slot />
    </div>
</div>