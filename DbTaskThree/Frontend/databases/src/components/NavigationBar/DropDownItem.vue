<template>
    <li @click="this.$emit('getQuery', itemText)"><a class="dropdown-item">{{ itemText }}</a></li>
</template>

<script>
export default {
    name: "DropDownItem",
    props: ['itemText']
}
</script>

<style scoped>
.dropdown-item {
    cursor: pointer;
}
</style>