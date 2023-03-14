<template>
    <div v-if="enabledFlag" class="form-floating">
        <textarea 
            ref="activeTextArea" 
            class="form-control" 
            placeholder="Leave a comment here" 
            id="floatingTextarea"
            :value="modelValue" 
            @change="$emit('update:modelValue', $event.target.value)"></textarea>
        <label for="floatingTextarea">SQL Query</label>
    </div>

    <div v-else class="form-floating">
        <textarea 
            readonly 
            class="form-control" 
            placeholder="Leave a comment here" 
            id="floatingTextarea" 
            :value="modelValue"
            @change="$emit('update:modelValue', $event.target.value)" 
            @dblclick="doubleClickEvent"></textarea>
        <label for="floatingTextarea">Result</label>
    </div>
</template>

<script>
export default {
    name: "MainTextArea",
    props: ['enabledFlag', 'modelValue'],
    emits: ['update:modelValue', 'dblClick'],
    components: {},
    methods: {
        doubleClickEvent() {
            this.$emit('dblClick');
            setTimeout(() => {
                if (this.$refs.activeTextArea !== null) {
                    this.$refs.activeTextArea.focus()
                }
            }, 100)
        }
    }
}
</script>

<style scoped>
.form-floating {
    height: 100%;
}

#floatingTextarea {
    height: 100%;
    resize: none;
}
</style>