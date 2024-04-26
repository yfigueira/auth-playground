<script lang="ts">
    import Registration from "./components/Registration.svelte";
    import type {RegistrationForm} from "$lib/model/registration-form";

    let formData: RegistrationForm = {
        firstName: '',
        lastName: '',
        email: '',
        password: '',
        repeatedPassword: ''
    }

</script>

<Registration {formData} />