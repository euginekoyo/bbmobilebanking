import React from 'react';

const EntitiesMenu = () => {
  return (
    <>
      {/* prettier-ignore */}
      <MenuItem icon="asterisk" to="/billers">
        <Translate contentKey="global.menu.entities.billers" />
      </MenuItem>
      <MenuItem icon="asterisk" to="/branches">
        <Translate contentKey="global.menu.entities.branches" />
      </MenuItem>
      <MenuItem icon="asterisk" to="/channels">
        <Translate contentKey="global.menu.entities.channels" />
      </MenuItem>
      <MenuItem icon="asterisk" to="/charge">
        <Translate contentKey="global.menu.entities.charge" />
      </MenuItem>
      <MenuItem icon="asterisk" to="/chargeranges">
        <Translate contentKey="global.menu.entities.chargeranges" />
      </MenuItem>
      <MenuItem icon="asterisk" to="/customer">
        <Translate contentKey="global.menu.entities.customer" />
      </MenuItem>
      <MenuItem icon="asterisk" to="/customeraccount">
        <Translate contentKey="global.menu.entities.customeraccount" />
      </MenuItem>
      <MenuItem icon="asterisk" to="/limits">
        <Translate contentKey="global.menu.entities.limits" />
      </MenuItem>
      <MenuItem icon="asterisk" to="/linkedaccounts">
        <Translate contentKey="global.menu.entities.linkedaccounts" />
      </MenuItem>
      <MenuItem icon="asterisk" to="/messagessms">
        <Translate contentKey="global.menu.entities.messagessms" />
      </MenuItem>
      <MenuItem icon="asterisk" to="/messagetemplates">
        <Translate contentKey="global.menu.entities.messagetemplates" />
      </MenuItem>
      <MenuItem icon="asterisk" to="/pinresethistory">
        <Translate contentKey="global.menu.entities.pinresethistory" />
      </MenuItem>
      <MenuItem icon="asterisk" to="/range">
        <Translate contentKey="global.menu.entities.range" />
      </MenuItem>
      <MenuItem icon="asterisk" to="/requests">
        <Translate contentKey="global.menu.entities.requests" />
      </MenuItem>
      <MenuItem icon="asterisk" to="/servicemanagement">
        <Translate contentKey="global.menu.entities.servicemanagement" />
      </MenuItem>
      <MenuItem icon="asterisk" to="/transactions">
        <Translate contentKey="global.menu.entities.transactions" />
      </MenuItem>
      {/* jhipster-needle-add-entity-to-menu - JHipster will add entities to the menu here */}
    </>
  );
};

export default EntitiesMenu;
