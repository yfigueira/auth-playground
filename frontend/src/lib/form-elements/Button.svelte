<script lang="ts">
    export let type: "button" | "submit" = "button";
    export let className: string = '';
</script>

<button {type} on:click class="border-2 rounded-xl h-12 w-1/2 text-zinc-100 text-xl {className}">
    <slot />
</button>