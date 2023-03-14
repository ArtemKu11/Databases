<template>
    <!-- <button type="button" class="btn btn-primary" v-on:click="$emit('buttonClick')">Отправить</button> -->
    <button v-if="enabledFlag" type="button" class="btn btn-primary" v-on:click="this.$emit('buttonClick')">Отправить</button>
    <button v-else type="button" class="btn btn-primary" v-on:click="this.$emit('buttonClick')">Очистить форму</button>
</template>

<script>
export default {
    name: "UnderButton",
    components: {},
    props: ["enabledFlag"],
    emits: ['buttonClick']
}
</script>

<style></style>