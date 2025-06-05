import { defineComponent, ref, inject } from 'vue';
import { useI18n } from 'vue-i18n';
import JhiNavbar from '@/core/jhi-navbar/jhi-navbar.vue';
import JhiSidebar from '@/core/jhi-navbar/jhisidebar.vue';
import JhiFooter from '@/core/jhi-footer/jhi-footer.vue';
import Ribbon from '@/core/ribbon/ribbon.vue';
import LoginForm from '@/account/login-form/login-form.vue';
import type LoginService from '@/account/login.service';
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
    const { t } = useI18n();
    const t$ = t;
    const toggleSidebar = () => {
      sidebarVisible.value = !sidebarVisible.value;
    };

    const handleSidebarToggle = isExpanded => {
      sidebarVisible.value = isExpanded;
    };

    const closeSidebar = () => {
      sidebarVisible.value = false;
    };

    // Placeholder for ribbonEnv and showLogin, as these may depend on JHipster auth
    const ribbonEnv = ref(''); // Adjust based on JHipster config (e.g., env from store)
    const showLogin = ref(false); // Adjust based on auth state
    const loginService = inject<LoginService>('loginService');

    const authenticated = inject<ComputedRef<boolean>>('authenticated');
    const username = inject<ComputedRef<string>>('currentUsername');

    const openLogin = () => {
      loginService.openLogin();
    };

    return {
      sidebarVisible,
      toggleSidebar,
      handleSidebarToggle,
      closeSidebar,
      t$,
      authenticated,
      username,
      openLogin,
      ribbonEnv,
      showLogin,
    };
  },
});
