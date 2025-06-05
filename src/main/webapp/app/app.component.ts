import { defineComponent, ref } from 'vue';
import { useI18n } from 'vue-i18n';
import JhiNavbar from '@/core/jhi-navbar/jhi-navbar.vue';
import JhiSidebar from '@/core/jhi-navbar/jhisidebar.vue';
import JhiFooter from '@/core/jhi-footer/jhi-footer.vue';
import Ribbon from '@/core/ribbon/ribbon.vue';
import LoginForm from '@/account/login-form/login-form.vue';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'App',
  components: {
    ribbon: Ribbon,
    'jhi-navbar': JhiNavbar,
    'jhi-sidebar': JhiSidebar,
    'jhi-footer': JhiFooter,
    'login-form': LoginForm,
  },
  setup() {
    const sidebarVisible = ref(false);

    const toggleSidebar = () => {
      sidebarVisible.value = !sidebarVisible.value;
    };

    const closeSidebar = () => {
      sidebarVisible.value = false;
    };

    return {
      sidebarVisible,
      toggleSidebar,
      closeSidebar, // Add this missing method
      t$: useI18n().t,
    };
  },
});
