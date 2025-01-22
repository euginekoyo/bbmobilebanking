export interface ICustomer {
  id?: number;
  customername?: string | null;
  phonenumber?: string;
  cardnumber?: string | null;
  accountnumber?: string;
  lang?: string | null;
  pin?: string | null;
  firstlogin?: string | null;
  active?: string | null;
  registered?: number | null;
  cstdelete?: number | null;
  regdate?: Date | null;
  alertenabled?: number | null;
  remark?: string | null;
  imsi?: string | null;
  partiallyregistered?: string | null;
  partialdate?: Date | null;
  registerdate?: Date | null;
  approved?: number | null;
  approvedby?: string | null;
  approveddate?: Date | null;
  declined?: number | null;
  declinedby?: string | null;
  declineddate?: Date | null;
  checkerremarks?: string | null;
  postaladdress?: string | null;
  residence?: string | null;
  dob?: Date | null;
  createdby?: string | null;
  emailaddress?: string | null;
  identificationid?: string | null;
  addaccount?: number | null;
  aclinkinginstitution?: string | null;
  deactivated?: number | null;
  deactivatedby?: string | null;
  deactivatedon?: Date | null;
  phonenochanged?: number | null;
  phonenochangedby?: string | null;
  phonenochangedon?: Date | null;
  originalphoneno?: string | null;
  newphoneno?: string | null;
  reset?: number | null;
  resetinginstitution?: string | null;
  pinresetremark?: string | null;
  resetby?: string | null;
  reseton?: Date | null;
  unblockinginstitution?: string | null;
  pinblock?: number | null;
  pinblockby?: string | null;
  pinblockremarks?: string | null;
  blockinginstitution?: string | null;
  pinblockon?: Date | null;
  approvedon?: Date | null;
  pinunblockby?: string | null;
  loggedin?: number | null;
  trials?: string | null;
  idtype?: string | null;
  idnumber?: string | null;
  gender?: string | null;
  cif?: string | null;
  dateofbirth?: Date | null;
  remarks?: string | null;
  resetimsi?: number | null;
  imsiresetby?: string | null;
  firstname?: string | null;
  secondname?: string | null;
  lastname?: string | null;
  pinblocktime?: string | null;
  customerstatus?: string | null;
  username?: string | null;
  password?: string | null;
  deviceid?: string | null;
  channel?: string | null;
  passreset?: number | null;
  passresetby?: string | null;
  passreseton?: Date | null;
  passblock?: number | null;
  passblockby?: string | null;
  passblockon?: Date | null;
  pinmarkblock?: number | null;
  passmarkblock?: number | null;
  passresetremarks?: string | null;
  passblockremarks?: string | null;
  passunblockby?: string | null;
  passtrials?: number | null;
  appactive?: number | null;
  lastlogin?: string | null;
  appmarkeddisable?: number | null;
  disableby?: string | null;
  approvedisableby?: string | null;
  appmarkedenable?: number | null;
  enableby?: string | null;
  approvedenableby?: string | null;
  markeddeactivate?: number | null;
  appfirstlogin?: string | null;
  atmtrials?: number | null;
  shorcuts?: string | null;
  markedactivate?: string | null;
  town?: string | null;
  approveddisableon?: Date | null;
  disabledon?: Date | null;
  resetapproveon?: Date | null;
  deletedby?: string | null;
  questionsasked?: string | null;
  questionstrials?: string | null;
  questionsanswered?: string | null;
  validotp?: number | null;
  activatedby?: string | null;
  activatedon?: Date | null;
  branchcode?: string | null;
}

export class Customer implements ICustomer {
  constructor(
    public id?: number,
    public customername?: string | null,
    public phonenumber?: string,
    public cardnumber?: string | null,
    public accountnumber?: string,
    public lang?: string | null,
    public pin?: string | null,
    public firstlogin?: string | null,
    public active?: string | null,
    public registered?: number | null,
    public cstdelete?: number | null,
    public regdate?: Date | null,
    public alertenabled?: number | null,
    public remark?: string | null,
    public imsi?: string | null,
    public partiallyregistered?: string | null,
    public partialdate?: Date | null,
    public registerdate?: Date | null,
    public approved?: number | null,
    public approvedby?: string | null,
    public approveddate?: Date | null,
    public declined?: number | null,
    public declinedby?: string | null,
    public declineddate?: Date | null,
    public checkerremarks?: string | null,
    public postaladdress?: string | null,
    public residence?: string | null,
    public dob?: Date | null,
    public createdby?: string | null,
    public emailaddress?: string | null,
    public identificationid?: string | null,
    public addaccount?: number | null,
    public aclinkinginstitution?: string | null,
    public deactivated?: number | null,
    public deactivatedby?: string | null,
    public deactivatedon?: Date | null,
    public phonenochanged?: number | null,
    public phonenochangedby?: string | null,
    public phonenochangedon?: Date | null,
    public originalphoneno?: string | null,
    public newphoneno?: string | null,
    public reset?: number | null,
    public resetinginstitution?: string | null,
    public pinresetremark?: string | null,
    public resetby?: string | null,
    public reseton?: Date | null,
    public unblockinginstitution?: string | null,
    public pinblock?: number | null,
    public pinblockby?: string | null,
    public pinblockremarks?: string | null,
    public blockinginstitution?: string | null,
    public pinblockon?: Date | null,
    public approvedon?: Date | null,
    public pinunblockby?: string | null,
    public loggedin?: number | null,
    public trials?: string | null,
    public idtype?: string | null,
    public idnumber?: string | null,
    public gender?: string | null,
    public cif?: string | null,
    public dateofbirth?: Date | null,
    public remarks?: string | null,
    public resetimsi?: number | null,
    public imsiresetby?: string | null,
    public firstname?: string | null,
    public secondname?: string | null,
    public lastname?: string | null,
    public pinblocktime?: string | null,
    public customerstatus?: string | null,
    public username?: string | null,
    public password?: string | null,
    public deviceid?: string | null,
    public channel?: string | null,
    public passreset?: number | null,
    public passresetby?: string | null,
    public passreseton?: Date | null,
    public passblock?: number | null,
    public passblockby?: string | null,
    public passblockon?: Date | null,
    public pinmarkblock?: number | null,
    public passmarkblock?: number | null,
    public passresetremarks?: string | null,
    public passblockremarks?: string | null,
    public passunblockby?: string | null,
    public passtrials?: number | null,
    public appactive?: number | null,
    public lastlogin?: string | null,
    public appmarkeddisable?: number | null,
    public disableby?: string | null,
    public approvedisableby?: string | null,
    public appmarkedenable?: number | null,
    public enableby?: string | null,
    public approvedenableby?: string | null,
    public markeddeactivate?: number | null,
    public appfirstlogin?: string | null,
    public atmtrials?: number | null,
    public shorcuts?: string | null,
    public markedactivate?: string | null,
    public town?: string | null,
    public approveddisableon?: Date | null,
    public disabledon?: Date | null,
    public resetapproveon?: Date | null,
    public deletedby?: string | null,
    public questionsasked?: string | null,
    public questionstrials?: string | null,
    public questionsanswered?: string | null,
    public validotp?: number | null,
    public activatedby?: string | null,
    public activatedon?: Date | null,
    public branchcode?: string | null,
  ) {}
}
